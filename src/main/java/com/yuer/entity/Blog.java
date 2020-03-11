package com.yuer.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blog {

	private Long id;
	private String title; // 博客标题
	private String firstPicture; // 首图
	private String content; // 内容
	private String flag;
	private Integer viewCounts; // 游览次数
	private Boolean isAppreciate; // 是否开启赞赏
	private Boolean isOpenCopyright; // 是否开启装载声明
	private Boolean isComment; // 是否开启评论
	private Boolean isPublished; // 是否发布
	private Boolean isRecommend; // 是否推荐

	private Date createTime; // 创建时间
	private Date updateTime; // 更新时间

	private User user;
	private List<Tag> tags = new ArrayList<>();
	private Type type;
	private List<Comment> comments = new ArrayList<>();

	private String tagIds;

	// 描述
	private String description;

	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstPicture() {
		return firstPicture;
	}

	public void setFirstPicture(String firstPicture) {
		this.firstPicture = firstPicture;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getViewCounts() {
		return viewCounts;
	}

	public void setViewCounts(Integer viewCounts) {
		this.viewCounts = viewCounts;
	}

	public Boolean getIsAppreciate() {
		return isAppreciate;
	}

	public void setIsAppreciate(Boolean isAppreciate) {
		this.isAppreciate = isAppreciate;
	}

	public Boolean getIsOpenCopyright() {
		return isOpenCopyright;
	}

	public void setIsOpenCopyright(Boolean isOpenCopyright) {
		this.isOpenCopyright = isOpenCopyright;
	}

	public Boolean getIsComment() {
		return isComment;
	}

	public void setIsComment(Boolean isComment) {
		this.isComment = isComment;
	}

	public Boolean getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}

	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Blog() {
		super();
	}

	

}
