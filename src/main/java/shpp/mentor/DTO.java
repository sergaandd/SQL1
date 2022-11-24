package shpp.mentor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

public class DTO {
    @NotNull(message = "This field must be filled")
    @IsFirstCapital(message="First letter must be capital")//My validator
    private String name;
    @NotNull(message = "This field must be filled")
    @Positive(message = "This field must be bigger zero")
    private int arg1;
    @NotNull(message = "This field must be filled")
    @Positive(message = "This field must be bigger zero")
    private int arg2;
    @NotNull(message = "This field must be filled")
    @Positive(message = "This field must be bigger zero")
    private float arg3;



     DTO(){}

    public String getName() {
        return name;
    }

    public DTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getArg1() {
        return arg1;
    }


    public DTO setArg1(int arg1) {
        this.arg1 = arg1;
        return this;
    }
    public int getArg2() {
        return arg2;
    }

    public DTO setArg2(int arg2) {
        this.arg2 = arg2;
        return this;
    }

    public float getArg3() {
        return arg3;
    }

    public DTO setArg3(float arg3) {
        this.arg3 = arg3;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DTO dto = (DTO) o;
        return Objects.equals(name, dto.name);
    }

    @Override
    public String toString() {
        return "DTO{" +
                "name='" + name + '\'' +
                ", Arg1=" + arg1 +
                ", Arg2=" + arg2 +
                ", Arg3=" + arg3 +
                '}';
    }

}
