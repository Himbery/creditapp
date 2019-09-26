package com.testytest.cr_lifehack_1.banner;

import com.google.gson.annotations.SerializedName;


public class BannerModel {

	@SerializedName("redirect_link")
	private String redirectLink;

	@SerializedName("image_link")
	private String imageLink;

	@SerializedName("banner_id")
	private int bannerId;

	public void setRedirectLink(String redirectLink){
		this.redirectLink = redirectLink;
	}

	public String getRedirectLink(){
		return redirectLink;
	}

	public void setImageLink(String imageLink){
		this.imageLink = imageLink;
	}

	public String getImageLink(){
		return imageLink;
	}

	public void setBannerId(int bannerId){
		this.bannerId = bannerId;
	}

	public int getBannerId(){
		return bannerId;
	}

	@Override
 	public String toString(){
		return 
			"BannerModel{" + 
			"redirect_link = '" + redirectLink + '\'' + 
			",image_link = '" + imageLink + '\'' + 
			",banner_id = '" + bannerId + '\'' + 
			"}";
		}
}