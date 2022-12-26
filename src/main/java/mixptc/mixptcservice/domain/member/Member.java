package mixptc.mixptcservice.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Member {

    private Long id;

    @NotNull
    private String loginId;

    @NotNull
    private String name;

    @NotEmpty
    private String password;

    @NotNull
    private Long tel;




}
