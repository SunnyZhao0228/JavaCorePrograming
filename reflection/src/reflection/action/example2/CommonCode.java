package reflection.action.example2;

/**
 * @author: zhaoqw
 * @date: 2022/3/4 14:16
 */
public class CommonCode {
  private String code;
  private String name;
  private String instructions;
  /**
   * 是否内置
   */
  private boolean original;



  public boolean isOriginal() {
    return original;
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

  public void setOriginal(boolean original) {
    this.original = original;
  }

  public void bindOriginal(Object original) {
    this.original = (boolean) original;
  }
}
