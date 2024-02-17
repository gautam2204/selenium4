package relativeLocators;

public enum ELEMENTSTATE {
  VISIBLE("is_visible"),
  CLICKABLE("is_clickable"),
  DISPLAYED_NOT_CLICKABLE("is_displayed");

  ELEMENTSTATE(String condition) {}
}
