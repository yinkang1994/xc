package com.xuecheng.manage_cms.service.impl;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.*;
import com.xuecheng.manage_cms.dao.CmsPageRespository;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Author yinkang
 * @Date 2020/3/11 12:59
 * cms页面管理业务层实现
 */
@Service
public class CmsPageServiceImpl implements CmsPageService {

    @Autowired
    private CmsPageRespository cmsPageRespository;

    @Override
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {

        if (page <= 0) {
            //默认显示第一页
            page = 1;
        }
        page = page - 1;//mongodb接口第一页从0开始

        if (size <= 0) {
            //默认每页显示20
            page = 20;
        }
        //构建分页条件
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> pages;

        if (queryPageRequest == null) {
            //分页查询
            pages = cmsPageRespository.findAll(pageable);
        } else {
            //条件匹配器
            ExampleMatcher exampleMatcher = ExampleMatcher.matching();
            //设置页面别名模糊匹配,名称模糊查询
            exampleMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("pageName", ExampleMatcher.GenericPropertyMatchers.contains());

            //条件对象
            CmsPage cp = new CmsPage();
            if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
                cp.setSiteId(queryPageRequest.getSiteId());
            }
            if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
                cp.setPageAliase(queryPageRequest.getPageAliase());
            }
            if (StringUtils.isNotEmpty(queryPageRequest.getPageName())) {
                cp.setPageName(queryPageRequest.getPageName());
            }
            if (StringUtils.isNotEmpty(queryPageRequest.getPageType())) {
                cp.setPageType(queryPageRequest.getPageType());
            }
            Example<CmsPage> example = Example.of(cp, exampleMatcher);
            pages = cmsPageRespository.findAll(example, pageable);
        }
        //构建返回结果对象
        QueryResult<CmsPage> cmsPageQueryResult = new QueryResult<CmsPage>();
        cmsPageQueryResult.setList(pages.getContent());
        cmsPageQueryResult.setTotal(pages.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS, cmsPageQueryResult);
    }


    @Override
    public CmsPageResult add(CmsPage cmsPage) {
        //根据站点id、页面访问地址、页面名称查找页面是否存在
        CmsPage cp = cmsPageRespository.findBySiteIdAndPageNameAndPageWebPath(cmsPage.getSiteId(), cmsPage.getPageName(), cmsPage.getPageWebPath());
        if (cp != null) {
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        CmsPage save = cmsPageRespository.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS, save);

    }

    @Override
    public ResponseResult deleteByPageId(String pageId) {
        cmsPageRespository.deleteById(pageId);
        return new ResponseResult(CommonCode.SUCCESS);
    }


    @Override
    public CmsPageResult edit(String pageId, CmsPage cmsPage) {
        //先根据id查找该页面
        Optional<CmsPage> op = cmsPageRespository.findById(pageId);
        if (op.isPresent()) {
            CmsPage cp = op.get();
            //更新站点id
            cp.setSiteId(cmsPage.getSiteId());
            //更新模板id
            cp.setTemplateId(cmsPage.getTemplateId());
            //更新页面别名
            cp.setPageAliase(cmsPage.getPageAliase());
            //更新页面名称
            cp.setPageName(cmsPage.getPageName());
            //更新访问路径
            cp.setPageWebPath(cmsPage.getPageWebPath());
            //更新物理路径
            cp.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            //更新页面类型
            cp.setPageType(cmsPage.getPageType());
            //执行更新
            CmsPage save = cmsPageRespository.save(cp);
            if (save != null) {
                return new CmsPageResult(CommonCode.SUCCESS, save);
            }
        }
        return new CmsPageResult(CommonCode.FAIL, null);
    }

    @Override
    public CmsPageResult get(String pageId) {
        Optional<CmsPage> op = cmsPageRespository.findById(pageId);
        if (op.isPresent()) {
            CmsPage cmsPage = op.get();
            return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
        }
        return null;
    }
}
