package mixptc.mixptcservice.web.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class FindIdForm {


    private long findtel; //기본값0인이유를 모르겠음.


    private String findname;


    private String findid;
}
