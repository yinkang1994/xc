package com.xuecheng.cms;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.manage_cms.ManageCmsApplication;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @Author yinkang
 * @Date 2020/3/14 18:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManageCmsApplication.class)
public class CmsTest {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;

    //远程访问测试
    @Test
    public void restTemplateTest(){
        ResponseEntity<Map> entity = restTemplate.getForEntity("http://localhost:31001/cms/config/get/5a791725dd573c3574ee333f", Map.class);
        System.out.println("--------------->"+entity);
    }

    //根据站点id查询模板列表
    @Test
    public void cmsTemplateRepositoryTest(){
        List<CmsTemplate> list = cmsTemplateRepository.findBySiteId("5a751fab6abb5044e0d19ea1");
        System.out.println(list);
    }

}
