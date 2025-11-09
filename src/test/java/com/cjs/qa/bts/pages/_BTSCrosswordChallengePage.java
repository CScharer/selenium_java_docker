package com.cjs.qa.bts.pages;

import com.cjs.qa.core.Environment;
import com.cjs.qa.selenium.Page;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.IExtension;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.LocalDateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class _BTSCrosswordChallengePage extends Page {
  private final Map<Integer, String> mapGridSquares = new HashMap<>();
  private String[] lGridSquares = null;
  private final String ALPHABET = "A;B;C;D;E;F;G;H;I;J;K;L;M;N;O;P;Q;R;S;T;U;V;W;X;Y;Z";
  private final int SQAUARE_MIN = 4;
  private final int SQAUARE_MAX = 628;
  private final LocalDateTime now = LocalDateTime.now();
  public String sYear = String.valueOf(now.getYear());
  // public String sMonth =
  // now.format(DateTimeFormatter.ofPattern("MMMM",Locale.ENGLISH));
  public String sMonth = "November";
  private final String sScreenShot =
      "C:"
          + Constants.DELIMETER_PATH
          + "Temp"
          + Constants.DELIMETER_PATH
          + "Crossword"
          + Constants.DELIMETER_PATH
          + sYear
          + "_"
          + sMonth
          + ".png";
  public final String letters[] = ALPHABET.split(Constants.DELIMETER_LIST);

  public _BTSCrosswordChallengePage(WebDriver webDriver) {
    super(webDriver);
  }

  private final By buttonContinue = By.xpath(".//input[@value='Continue']");
  private final By buttonCheck = By.xpath(".//*[@id='game']/div[1]/div/div/span[.='Check']");
  private final By buttonSave = By.xpath(".//*[@id='game']/div[1]/div/div/span[.='Save']");
  private final By buttonSubmit = By.xpath(".//*[@id='game']/div[1]/div/div/span[.='Submit']");
  private final By editName = By.xpath("html/body/div[1]/form/div/table/tbody/tr[1]/td[2]/input");
  private final By editEmail = By.xpath("html/body/div[1]/form/div/table/tbody/tr[2]/td[2]/input");
  private final By buttonOK = By.xpath("html/body/div[1]/form/div/table/tbody/tr[3]/td/input");
  public String sPageTitle = "Take the Challenge";

  public void clickbuttonContinue() {
    clickObject(buttonContinue);
  }

  public void clickbuttonCheck() {
    clickObject(buttonCheck);
  }

  public void clickbuttonSave() {
    clickObject(buttonSave);
  }

  public void clickbuttonSubmit() {
    clickObject(buttonSubmit);
  }

  public void clickbuttonOK() {
    clickObject(buttonOK);
  }

  public void sovleTakeTheChallenge(String values) {
    // sYear = "2016";
    // sMonth = "September";
    // sScreenShot = "C:" + Constants.DELIMETER_PATH + "Temp" +
    // Constants.DELIMETER_PATH + "Crossword" + Constants.DELIMETER_PATH +
    // sYear + "_" + sMonth +
    // ".png";
    navigateCrosswordURL(sYear, sMonth);
    getGridSquares();
    String startLetter = "";
    if (!values.equals("")) {
      startLetter = populateFromFailure(values);
    }
    Environment.sysOut("startLetter:" + startLetter);
    for (final String letter : letters) {
      if (startLetter.equals("")) {
        // Do normal process.
        getCrosswordLetter(letter);
      } else {
        if (startLetter.equals(letter)) {
          getCrosswordLetter(letter);
          // Clear startLetter so the normal process continues.
          startLetter = "";
        }
      }
    }
    solvePuzzle();
    clickbuttonSubmit();
    submitPagePopulate();
    Environment.sysOut("DONE!");
  }

  public String populateFromFailure(String values) {
    values = values.replace(" ", "");
    // Z{512=S,514=N,6=E,524=L,529=S,530=E,531=L,532=F,533=S,534=E,23=H,535=R,536=V,537=I,26=C,538=C,539=E,540=P,541=A,542=S,31=X,543=S,544=W,545=O,546=R,547=D,548=R,549=E,550=S,551=E,552=T,556=E,46=C,558=E,48=A,560=T,562=N,51=I,53=F,54=A,55=L,56=C,57=O,58=R,69=P,71=O,73=R,585=A,587=K,76=S,78=O,79=D,81=L,94=R,606=E,607=M,96=N,608=A,609=I,98=D,610=L,611=S,612=I,101=C,613=G,614=N,103=R,615=A,104=V,616=T,617=U,106=A,618=R,619=E,620=B,621=R,622=A,623=N,624=D,625=I,626=N,627=G,119=O,121=F,123=W,126=O,128=W,129=A,131=I,144=B,146=L,148=A,151=S,153=A,154=N,155=U,156=M,157=B,158=E,159=R,160=I,161=N,162=Q,163=U,164=E,165=U,166=E,169=L,171=I,173=R,176=E,178=R,179=C,181=E,183=N,193=R,194=E,195=S,196=C,197=H,198=E,199=D,200=U,201=L,202=E,203=D,204=E,206=R,208=T,214=C,219=M,221=T,223=V,226=F,228=A,229=D,233=E,235=D,239=I,244=M,246=S,248=P,251=C,253=L,254=C,258=R,260=E,264=S,269=A,273=N,276=A,278=L,279=A,283=P,285=L,289=C,294=N,301=R,304=L,306=B,307=E,308=R,309=K,310=L,311=E,312=Y,313=N,314=O,315=R,316=T,317=H,318=P,319=A,320=C,321=I,322=F,323=I,324=C,326=E,329=L,333=I,335=G,339=D,344=G,351=P,354=I,358=S,360=L,364=E,368=R,369=E,370=D,371=U,372=C,373=E,374=D,375=C,376=O,377=S,378=T,379=N,383=E,385=O,389=S,394=M,399=A,401=R,404=G,408=L,410=B,414=K,419=E,424=D,426=T,429=R,431=M,433=I,435=A,437=H,439=P,444=N,449=S,451=A,454=U,456=O,458=C,460=L,462=E,464=H,469=T,474=R,476=L,479=L,481=B,483=E,485=P,487=L,489=O,499=O,504=E,506=I,508=N,510=O}
    // Z
    final String letter = values.substring(0, 1);
    // 512=S,514=N,6=E,524=L,529=S,530=E,531=L,532=F,533=S,534=E,23=H,535=R,536=V,537=I,26=C,538=C,539=E,540=P,541=A,542=S,31=X,543=S,544=W,545=O,546=R,547=D,548=R,549=E,550=S,551=E,552=T,556=E,46=C,558=E,48=A,560=T,562=N,51=I,53=F,54=A,55=L,56=C,57=O,58=R,69=P,71=O,73=R,585=A,587=K,76=S,78=O,79=D,81=L,94=R,606=E,607=M,96=N,608=A,609=I,98=D,610=L,611=S,612=I,101=C,613=G,614=N,103=R,615=A,104=V,616=T,617=U,106=A,618=R,619=E,620=B,621=R,622=A,623=N,624=D,625=I,626=N,627=G,119=O,121=F,123=W,126=O,128=W,129=A,131=I,144=B,146=L,148=A,151=S,153=A,154=N,155=U,156=M,157=B,158=E,159=R,160=I,161=N,162=Q,163=U,164=E,165=U,166=E,169=L,171=I,173=R,176=E,178=R,179=C,181=E,183=N,193=R,194=E,195=S,196=C,197=H,198=E,199=D,200=U,201=L,202=E,203=D,204=E,206=R,208=T,214=C,219=M,221=T,223=V,226=F,228=A,229=D,233=E,235=D,239=I,244=M,246=S,248=P,251=C,253=L,254=C,258=R,260=E,264=S,269=A,273=N,276=A,278=L,279=A,283=P,285=L,289=C,294=N,301=R,304=L,306=B,307=E,308=R,309=K,310=L,311=E,312=Y,313=N,314=O,315=R,316=T,317=H,318=P,319=A,320=C,321=I,322=F,323=I,324=C,326=E,329=L,333=I,335=G,339=D,344=G,351=P,354=I,358=S,360=L,364=E,368=R,369=E,370=D,371=U,372=C,373=E,374=D,375=C,376=O,377=S,378=T,379=N,383=E,385=O,389=S,394=M,399=A,401=R,404=G,408=L,410=B,414=K,419=E,424=D,426=T,429=R,431=M,433=I,435=A,437=H,439=P,444=N,449=S,451=A,454=U,456=O,458=C,460=L,462=E,464=H,469=T,474=R,476=L,479=L,481=B,483=E,485=P,487=L,489=O,499=O,504=E,506=I,508=N,510=O
    final String letters = values.substring(2, (values.length() - 1));
    final String gridSquares[] = letters.split(",");
    for (final String gridSquare : gridSquares) {
      Environment.sysOut(gridSquare);
      final String gridValue[] = gridSquare.split("=");
      final int iGridSquare = Integer.valueOf(gridValue[0]);
      String sLetter = "";
      try {
        sLetter = gridValue[1];
      } catch (final Exception e) {
        Environment.sysOut(gridValue);
      }
      mapGridSquares.put(iGridSquare, sLetter);
    }
    return letter;
  }

  public void submitPagePopulate() {
    String userName = System.getenv("USERNAME");
    userName = System.getProperty("user.name");
    setEdit(editName, "Christopher J. Scharer");
    setEdit(editEmail, userName + "@WRBerkley" + IExtension.COM);
    clickbuttonOK();
  }

  public void navigateCrosswordURL(String sYear, String sMonth) {
    final String sURL =
        "http://crossword.info/btsclientservices/" + sMonth + "_" + sYear + "/"; // final
    // String
    // sURL
    // =
    // "http://crossword.info/btsclientservices/June_2016_a";
    webDriver.get(sURL);
    final String actual = webDriver.getTitle();
    if (actual.equals("Web Page Restricted")) {
      clickbuttonContinue();
    }
    verifyPage(sYear, sMonth);
  }

  public void verifyPage(String sYear, String sMonth) {
    sPageTitle += " " + sMonth + " " + sYear;
    verifyTitle(sPageTitle);
  }

  public void getGridSquares() {
    for (int iGridSquare = SQAUARE_MIN; iGridSquare <= SQAUARE_MAX; iGridSquare++) {
      final WebElement oDiv =
          webDriver.findElement(By.xpath(".//*[@id='game']//div[" + iGridSquare + "]"));
      final String sClass = oDiv.getAttribute("class");
      if (sClass.equals("GridSquare Let")) {
        mapGridSquares.put(iGridSquare, "");
      }
    }
    final String sKeySet =
        mapGridSquares.keySet().toString().replace("[", "").replace("]", "").replace(" ", "");
    lGridSquares = sKeySet.split(",");
  }

  public void getCrosswordLetter(String sLetter) {
    Environment.sysOut(sLetter);
    solvePuzzle(sLetter);
    Environment.sysOut(mapGridSquares.toString());
    webDriver.navigate().refresh();
    sleep(1);
  }

  protected boolean isElementPresent(By by) {
    try {
      webDriver.findElement(by);
      return true;
    } catch (final Exception e) {
      return false;
    }
  }

  public void solvePuzzle(String sLetter) {
    for (final String sGridSquare : lGridSquares) {
      final int iGridSquare = Integer.valueOf(sGridSquare);
      final WebElement oDiv =
          webDriver.findElement(By.xpath(".//*[@id='game']//div[" + iGridSquare + "]"));
      // final JavascriptExecutor js = (JavascriptExecutor) webDriver;
      // js.executeScript("arguments[0].innerText = '" + letter + "'",
      // oSpan);
      final WebElement oSpan = oDiv.findElement(By.xpath("./p/span"));
      ((JavascriptExecutor) webDriver)
          .executeScript("arguments[0].innerText = '" + sLetter + "'", oSpan);
    }
    clickbuttonCheck();
    for (final String sGridSquare : lGridSquares) {
      final int iGridSquare = Integer.valueOf(sGridSquare);
      if (mapGridSquares.get(iGridSquare).equals("")) {
        if (isElementPresent(
            By.xpath(".//*[@id='game']//div[" + iGridSquare + "][not(*/div[.='X'])]"))) {
          mapGridSquares.put(iGridSquare, sLetter);
        }
      }
    }
  }

  public void solvePuzzle() {
    for (final String sGridSquare : lGridSquares) {
      final int iGridSquare = Integer.valueOf(sGridSquare);
      final WebElement oDiv =
          webDriver.findElement(By.xpath(".//*[@id='game']//div[" + iGridSquare + "]"));
      final WebElement oSpan = oDiv.findElement(By.xpath("./p/span"));
      ((JavascriptExecutor) webDriver)
          .executeScript(
              "arguments[0].innerText = '" + mapGridSquares.get(iGridSquare) + "'", oSpan);
    }
    clickbuttonCheck();
    captureScreenshot(sScreenShot);
  }
}
