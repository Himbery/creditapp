package com.testytest.cr_lifehack_1.banner;


import com.google.gson.annotations.SerializedName;


public class AdsConfigModel {

	@SerializedName("showOwnBanners")
	private boolean showOwnBanners;

	@SerializedName("showSmallAdsBanners")
	private boolean showSmallAdsBanners;

	@SerializedName("showAdsBanners")
	private boolean showAdsBanners;

	public void setShowOwnBanners(boolean showOwnBanners){
		this.showOwnBanners = showOwnBanners;
	}

	public boolean isShowOwnBanners(){
		return showOwnBanners;
	}

	public void setShowSmallAdsBanners(boolean showSmallAdsBanners){
		this.showSmallAdsBanners = showSmallAdsBanners;
	}

	public boolean isShowSmallAdsBanners(){
		return showSmallAdsBanners;
	}

	public void setShowAdsBanners(boolean showAdsBanners){
		this.showAdsBanners = showAdsBanners;
	}

	public boolean isShowAdsBanners(){
		return showAdsBanners;
	}

	@Override
 	public String toString(){
		return 
			"AdsConfigModel{" + 
			"showOwnBanners = '" + showOwnBanners + '\'' + 
			",showSmallAdsBanners = '" + showSmallAdsBanners + '\'' + 
			",showAdsBanners = '" + showAdsBanners + '\'' + 
			"}";
		}
}