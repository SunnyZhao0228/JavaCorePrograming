package reflection.action.example2;




import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 数据对象
 *
 * @author liuzw
 * @date 2021/07/22
 */

public class CommonCodeDO {
  /**
   * 编号
   */
  private String code;

  /**
   * 名称
   */
  private String name;

  /**
   * 说明
   */
  private String instructions;

  /**
   * 是否内置
   */
  private boolean original;


  public static CommonCodeDO from(CommonCode commonCode) {
    CommonCodeDO convert = BeanCopierUtils.convert(commonCode, CommonCodeDO.class);
    convert.setOriginal(commonCode.isOriginal());
    return convert;
  }

  public CommonCode to() {
    CommonCode convert = BeanCopierUtils.convert(this, CommonCode.class);
    convert.bindOriginal(this.getOriginal());
    return convert;
  }



  public static List<CommonCode> toList(List<CommonCodeDO> codeDOS) {
    if (CollectionUtils.isEmpty(codeDOS)) {
      return Collections.emptyList();
    }
    List<CommonCode> codes = new ArrayList<>();
    codeDOS.forEach(commonCodeDO -> codes.add(commonCodeDO.to()));
    return codes;
  }

  public CommonCodeDTO toDTO() {
    CommonCodeDTO convert = BeanCopierUtils.convert(this, CommonCodeDTO.class);
    convert.setOriginal((Boolean) this.getOriginal());
    return convert;
  }

  public static List<CommonCodeDTO> toDTOs(List<CommonCodeDO> commonCodeDOS) {
    if (CollectionUtils.isEmpty(commonCodeDOS)) {
      return Collections.emptyList();
    }
    List<CommonCodeDTO> dtos = new ArrayList<>();
    commonCodeDOS.forEach(commonCodeDO -> dtos.add(commonCodeDO.toDTO()));
    return dtos;
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

  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public boolean isOriginal() {
    return original;
  }

  public void setOriginal(boolean original) {
    this.original = original;
  }

  private Object getOriginal() {
    return this.original;
  }
}
