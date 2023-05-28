package com.itwill.ilhajob.user.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.itwill.ilhajob.common.entity.App;
import com.itwill.ilhajob.common.entity.Blog;
import com.itwill.ilhajob.common.entity.BlogComment;
import com.itwill.ilhajob.common.entity.Payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@SequenceGenerator(name = "user_id_SEQ_gen",
				   sequenceName = "user_id_SEQ",
				   allocationSize = 1)
@Table(name = "userinfo")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "user_id_SEQ_gen")
	private Long id;

	@NonNull
	@Column(nullable = false)
	private String userEmail;
	
	@NonNull
	@Column(nullable = false)
	private String userPassword;
	
	private String userName;
	
	private String userPhone;
	
	//private LocalDateTime userCreateDate;
	
	private String userSex;
	
	@Column
	private Integer userPostCode;

	@Column(length = 600)
	private String userAddress;
	
	@Column(length = 600)
	private String userAddressDetail;
		
	//private String userCareer;
	
	private Integer userAge;
	
	//private String userFinalEducation;
	
	private String userLanguage;
	
	@Column(length = 300)
	private String userSkills;
	
	private String userImage;
	
	private String snsType;
	
	private String snsId;
	
	//private String job;
	@Column(columnDefinition = "NUMBER(1) DEFAULT 0")
	private Integer paymentStatus;
	
	@Column(columnDefinition = "NUMBER(1) DEFAULT 0")
	private Integer writeStatus;
	
	private int role;
	
	private int state;
	
    @PrePersist
    public void prePersist() {
        this.role = this.role == 0 ? 1 : this.role;
    }
}
