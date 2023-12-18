package mixptc.mixptcservice.web.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class FindpasswordForm {


    private String findId;
    
    private String findname;

    private String findpassword;
}
