
package com.cos.blog.model;

import javax.annotation.Generated;

import lombok.Data;

@Data
@Generated("jsonschema2pojo")
public class KakaoProfile {

	public Long id;
	public String connected_at;
	public Properties properties;
	public Kakao_account kakao_account;
	
	@Data
	@Generated("jsonschema2pojo")
	public class Properties {
		public String nickname;
		public String profile_image;
		public String thumbnail_image;

	}
	@Data
	@Generated("jsonschema2pojo")
	public class Kakao_account {

		public Boolean profile_nickname_needs_agreement;
		public Boolean profile_image_needs_agreement;
		public Profile profile;
		@Data
		@Generated("jsonschema2pojo")
		public class Profile {

			public String nickname;
			public String thumbnail_image_url;
			public String profile_image_url;
			public Boolean is_default_image;
			public Boolean is_default_nickname;

		}

	}
}
