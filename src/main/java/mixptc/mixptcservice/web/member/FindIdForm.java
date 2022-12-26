package mixptc.mixptcservice.web.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class FindIdForm {

    @NotEmpty
    private long findtel;

    @NotEmpty
    private String findname;
}
