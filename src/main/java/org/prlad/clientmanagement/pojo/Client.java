package org.prlad.clientmanagement.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@ToString(exclude = { "user" }) //如果不加，他會印出user然後user再印出client無限迴圈
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "nvarchar(256)", nullable = false)
    private String clientId;

    @Column(columnDefinition = "nvarchar(256)", nullable = false)
    private String clientName;

    @Column(columnDefinition = "nvarchar(16)", nullable = false)
    private String role;

    @Column(columnDefinition = "nvarchar(256)")
    private String secret;

    /**
     * 錢包公鑰。
     */
    @Column(name = "address", nullable = false, length = 42)
    private String address;

    /**
     * 錢包私鑰。
     * @ColumnTransformer(read = "AES_DECRYPT(private_key, 'OtherPeopleNeverKnow')", write = "AES_ENCRYPT(?, 'OtherPeopleNeverKnow')")
     */
    @Column(name = "private_key", nullable = false, length = 255)
    private byte[] privateKey;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

}
