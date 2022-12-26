package mixptc.mixptcservice.web.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class FindpasswordForm {

    @NotEmpty
    private String findId;

    @NotEmpty
    private long findtel;
}
