package usa.ciclo4.app.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usuarios")
public class User implements Serializable {

    @Id
    private Integer id;
    private String identification;
    private String name;
    private Date bithtDay;
    private String monthBirthtDay;
    private String address;
    private String cellPhone;
    private String email;
    private String password;
    private String zone;
    private String type;
    /*
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    @Column(name = "user_email", nullable = false, length = 50)
    private String email;
    @NonNull
    @Column(name = "user_password", nullable = false,  length = 50)
    private String password;
    @NonNull
    @Column(name = "user_name", nullable = false,  length = 80)
    private String  name;*/


}