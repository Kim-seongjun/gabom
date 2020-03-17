package icia.project.gabom.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import icia.project.gabom.dao.SnsCommentDao;
import icia.project.gabom.dto.Member;
import icia.project.gabom.dto.SnsCommentDto;
import icia.project.gabom.dto.SnsCommentResult;
import icia.project.gabom.dto.SnsLikeHateCounter;

@Service
public class SnsComment {

	@Autowired
	SnsCommentDao snsCommentDao;
	
	//댓글 목록 프로필 사진 가져와야됨
	public String snsComment(int postNumber) {
		String json=null;
		List<SnsCommentResult> snsCommentResultList=new ArrayList<SnsCommentResult>();
		SnsCommentResult snsCommentResult= new SnsCommentResult();
		List<SnsCommentDto> commentList=snsCommentDao.snsComment(postNumber);
		JsonObject jsonobject=null;
		if(commentList.size()==0) {
			jsonobject= new JsonObject();
			jsonobject.addProperty("comment", "댓글이 없습니다.");
			return json=new Gson().toJson(jsonobject);
		}else{
			for(SnsCommentDto comment : commentList) {
					Member profilePic= new Member();
					SnsLikeHateCounter snsLikeHateCounter= new SnsLikeHateCounter();
					snsLikeHateCounter=snsCommentDao.commentLikeHateGet(comment.getCommentNumber());
					profilePic=snsCommentDao.snsCommentProfilePic(comment.getId());
					snsCommentResult=setResult(profilePic,comment,snsLikeHateCounter);
					snsCommentResultList.add(snsCommentResult);
			}
		}
		json=new Gson().toJson(snsCommentResultList);
		return json;
	}

	private SnsCommentResult setResult(
			Member profilePic, SnsCommentDto comment, SnsLikeHateCounter snsLikeHateCounter) {
			SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
			String commentDate=format1.format(comment.getCommentDate());
			String editDate=format1.format(comment.getEditDate());
			SnsCommentResult snsCommentResult= new SnsCommentResult();
			snsCommentResult.setNumber(comment.getCommentNumber())
			.setContent(comment.getContent())
			.setId(comment.getId())
			.setDate(commentDate)
			.setEditDate(editDate)
			.setPostNumber(comment.getPostNumber())
			.setReport(comment.getReport())
			.setProfilePic(profilePic.getMember_profile_picture())
			.setLike(snsLikeHateCounter.getSnsLike())
			.setHate(snsLikeHateCounter.getSnsHate());
		return snsCommentResult;
	}
	
}
