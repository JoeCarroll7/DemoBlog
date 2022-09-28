package com.JoeBlog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ConfirmationToken {
	
	@Id
	@SequenceGenerator(
			name = "confirmation_token_sequence",
			sequenceName = "confirmation_token_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy=GenerationType.IDENTITY,
			generator="confirmation_token_sequence"
	)
	private Integer id;
	@Column(nullable = false)
	private String token;
	@Column(nullable = false)
	private LocalDateTime localDateTime;
	@Column(nullable = false)
	private LocalDateTime expiresAt;
	private LocalDateTime confirmedAt;
	
	@ManyToOne
	@JoinColumn(
			nullable = false,
			name = "user_id"
	)
	private User user;
	
	public ConfirmationToken(String token, LocalDateTime localDateTime, LocalDateTime expiresAt,
			User user) {
		super();
		this.token = token;
		this.localDateTime = localDateTime;
		this.expiresAt = expiresAt;
		this.confirmedAt = confirmedAt;
		this.user = user;
	}
}
