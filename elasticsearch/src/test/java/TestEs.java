import com.ghd.EsApplication;
import com.ghd.model.Order;
import com.ghd.service.OrderService;
import io.micrometer.core.instrument.search.Search;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = EsApplication.class )
@RunWith(SpringRunner.class)
public class TestEs {

    @Autowired
    OrderService orderService;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testes(){
        /*List<Order> byUsernameAndOrderNum = orderService.findByUsernameAndOrderNum("阿拉法特", "20203010341243");
        for (Order order : byUsernameAndOrderNum) {
            System.out.println(order);
        }*/

        List<Order> fl = orderService.findByProductName("屌爆了");
        for (Order order : fl) {
            System.out.println(order);
        }
    }

    /**
     * 复杂搜索分页操作 0第一页
     */
    @Test
    public void testfuza(){
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(matchAllQueryBuilder).setPageable(PageRequest.of(0, 2));
        Page<Order> search = orderService.search(nativeSearchQuery);
        search.forEach(p -> System.out.println(p));
    }

    /**
     * 分页 排序
     */
    @Test
    public void testsort(){

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withIndices("order")//设置查询索引
                .withTypes("order")//设置查询类型
                .withQuery(QueryBuilders.matchAllQuery())//设置查询条件
                .withSort(new FieldSortBuilder("price").order(SortOrder.ASC))//设置排序条件
                .withPageable(PageRequest.of(0, 3))//设置分页
                .build();

        AggregatedPage<Order> orders = elasticsearchTemplate.queryForPage(build, Order.class);
        orders.forEach(o -> System.out.println(o));

    }


    @Test
    public void testHigerLiger(){
        //高亮字段
        HighlightBuilder.Field field = new HighlightBuilder.Field("productName")
                .requireFieldMatch(false)//关闭检索字段
                .preTags("<span style = 'color:red'>")
                .postTags("</span>");





        SearchQuery build = new NativeSearchQueryBuilder()
                .withIndices("order")
                .withTypes("order")
                .withQuery(QueryBuilders.queryStringQuery("得劲").field("productName").field("username"))
                .withFilter(QueryBuilders.rangeQuery("price").lt(10000))
                .withSort(new FieldSortBuilder("price").order(SortOrder.ASC))
                .withPageable(PageRequest.of(0, 2))
                .withHighlightFields(field)
                .build();


        AggregatedPage<Order> orders = elasticsearchTemplate.queryForPage(build, Order.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                List<Order> orders = new ArrayList<>();

                //根据searchResponse获取hits
                SearchHits hits = searchResponse.getHits();
                //获取检索结果hits
                SearchHit[] hits1 = hits.getHits();
                //遍历
                for (SearchHit s : hits1) {
                    Order order = new Order();

                    //获取原始记录
                    Map<String, Object> sourceAsMap = s.getSourceAsMap();
                    //获取高亮Map
                    Map<String, HighlightField> highlightFields = s.getHighlightFields();
                    order.setId(sourceAsMap.get("id").toString());
                    order.setUsername(sourceAsMap.get("username").toString());
                    if(highlightFields.containsKey("username")){
                        order.setUsername(highlightFields.get("username").fragments()[0].toString());
                    }
                    order.setPrice(new BigDecimal(sourceAsMap.get("price").toString()));
                    order.setOrderNum(sourceAsMap.get("orderNum").toString());
                    order.setProductName(sourceAsMap.get("productName").toString());
                    if(highlightFields.containsKey("productName")){
                        order.setProductName(highlightFields.get("productName").fragments()[0].toString());

                    }
                    orders.add(order);
                }
                return new AggregatedPageImpl<T>((List<T>) orders);
            }
        });
        orders.forEach(o -> System.out.println(o));

    }



}


