<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="boardVote" style="margin:0px auto;text-align:center;">

	<a class="" href="#">
		<b><span id="boardVotedLike">0</span></b>
		<p>좋아요</p>	
	</a>
	<a class="" href="#">
		<b><span id="boardVotedDislike">0</span></b>
		<p>뒤져요</p>
	</a>
</div>

<style>
	.boardVote>a{border:2px solid #D35460;color:#D35460;display: inline-block;width:60px;height:60px;margin:0px 10px;text-decoration:none;border-radius:4px;}
	.boardVote>a>b{display: block; padding-top: 10px; font: bold 17px/1 Arial,sans-serif;}
	.boardVote>a>p{margin: 0;font-weight: 700;font-size: 10px;}
</style>