package reflection.action.example2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhaoqw
 * @date: 2022/3/4 14:24
 */
public class CommonCodeDTO {
  /**
   * 编号
   */
  private Long id;
  /**
   * 编码
   */
  private String code;
  /**
   * 名称
   */
  private String name;
  /**
   * 排序
   */
  private Boolean original;


  List<CommonCodeDTO> children;

  public static CommonCodeDTO from(CommonCode commonCode) {
    return BeanCopierUtils.convert(commonCode, CommonCodeDTO.class);
  }

  public static List<CommonCodeDTO> from(List<CommonCode> codes) {
    List<CommonCodeDTO> dtos = new ArrayList<>();
    codes.forEach(code -> {
      CommonCodeDTO dto = from(code);
      dtos.add(dto);
    });
    return dtos;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getOriginal() {
    return original;
  }

  public void setOriginal(Boolean original) {
    this.original = original;
  }
}
