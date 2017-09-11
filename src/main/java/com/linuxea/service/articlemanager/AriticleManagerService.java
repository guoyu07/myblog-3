package com.linuxea.service.articlemanager;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.linuxea.model.Article;
import com.linuxea.model.ArticleWithTag;
import com.linuxea.service.tagmanager.TagManagerService;
import com.linuxea.utils.IdKits;

import java.util.Date;
import java.util.List;

/**
 * Created by Linuxea on 2017-09-11.
 */
public class AriticleManagerService {

    public static final AriticleManagerService SERVICE = new AriticleManagerService();
	public static final TagManagerService TAG_MANAGER_SERVICE = TagManagerService.SERVICE;

	public Kv add(Article article, String labels) {
		List<String> labelsId = TAG_MANAGER_SERVICE.checkLabels(labels);
		Kv kv = Kv.create();
        article.setCreateTime(new Date());
        article.setId(IdKits.wantId());
        if (article.save()) {
            kv.set("stateCode", "ok");
        } else {
            kv.set("stateCode", "notok");
        }
		bindTag(labelsId, article.getId());
		return kv;
    }


	/**
	 * 标签绑定
	 *
	 * @param labelsId
	 * @param articleId
	 */
	private void bindTag(List<String> labelsId, String articleId) {
		for (String temp : labelsId) {
			String articleWithTagId = IdKits.wantId();
			ArticleWithTag articleWithTag = new ArticleWithTag();
			articleWithTag.setId(articleWithTagId);
			articleWithTag.setTagId(temp);
			articleWithTag.setArticleId(articleId);
			articleWithTag.save();
		}
	}


	public boolean update(Article article) {
		return article.update();
    }

    public boolean delete(Article article) {
        Db.update("delete from article_with_tag where article_id = ?", article.getId());
        Db.update("delete from article_with_kind where article_id = ?", article.getId());
        return article.delete();
    }

    /**
     * 列出文章每十条
     */
    public void find() {

    }

    /**
     * 加载一条详情
     *
     * @param id
     */
    public Article loadOne(String id) {
        return Article.dao.findById(id);
    }
}
