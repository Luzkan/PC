package luzkan;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

//Czas tworzenia: Czw [19:00-23:30] + Sb [16:00 - 24:00] + Ndz [9:00-15:30 i 16:40-22:00]
public class PaintKontroler {

    // Buttony, Menu i Canvasik // Zrobiony jest cleanup tylko do używanych

    @FXML
    private VBox caleOkno;

    @FXML
    private Pane tuRysuje;

    @FXML
    private ColorPicker wyborKoloru;

    @FXML
    private Text xLokacja;

    @FXML
    private Text yLokacja;

    // XxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXx

    private double ruszanyX, ruszanyY, koncowyX, koncowyY;

    @FXML
    void initialize() {

        // Kolor i wielkość tła

        tuRysuje.setStyle("-fx-background-color: white;");
        tuRysuje.setPrefSize(1280, 720);

        // Ogólna kontrola myszki

        tuRysuje.setOnMouseClicked( e -> WyborRysowanejFigury(narzedzie));

        tuRysuje.setOnMouseMoved( e ->{
            ruszanyX = e.getX();
            ruszanyY = e.getY();
            xLokacja.setText(String.valueOf(ruszanyX));
            yLokacja.setText(String.valueOf(ruszanyY));
        });

        tuRysuje.setOnMouseReleased( e ->{
            koncowyX = e.getX();
            koncowyY = e.getY();
            tuRysuje.setCursor(Cursor.DEFAULT);
        });
    }

    // XxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXx
    // Menu na górze

    ImageView wczytanyObrazeczek;
    
    @FXML
    void nowyKlik() {
        //Tu zgrabnie trzasnąć restart programu
    }

    @FXML
    void otworzKlik() {
        wczytanyObrazeczek = new ImageView();
        wczytajFotke();
        tuRysuje.getChildren().addAll(wczytanyObrazeczek);
    }

    @FXML
    void zapiszJakoKlik() {
        zgrajFotkeiZapisz();
    }

    @FXML
    void zapiszKlik() {
        zgrajFotkeiZapisz();
    }

    @FXML
    void pomocKlik() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Jak się używa painta?");
        alert.setHeaderText("Jak chcesz tworzyc wielokąt to pierwszy klik zatwierdza tworzenie.\n" +
                "Drugi klik to pierwszy róg wielokąta.\n" +
                "Stworzenie wielokąta zatwierdzasz prawym myszkiem.");
        String s ="Stworzył Marcel Jerzyk // 23.04.2018";
        alert.setContentText(s);
        alert.show();
    }

    @FXML
    void ficzeryKlik() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ficzerki");
        alert.setHeaderText("Opcje programu:\n" +
                "- Tworzenie prostokątów, kół\n" +
                "- Tworzenie dowolnych wielokątów\n" +
                "- Zmiana koloru (całe RGB) i wielkości figur\n" +
                "- Funkcja usuwania figur\n" +
                "- Automatyczne chowanie regulatorów wielkości\n" +
                "- Automatyczny reskaling okna do rysowania\n" +
                "- Bieżąca pozycja kursora myszy\n" +
                "- Funkcja zapisu i wczytania pliku\n" +
                "- Funkcja eksportu do png\n" +
                "- Funkcja tworzenia nowego okna i wyjścia");
        String s ="Stworzył Marcel Jerzyk // 23.04.2018";
        alert.setContentText(s);
        alert.show();
    }

    @FXML
    void bugiKlik() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Bugi");
        alert.setHeaderText("Mózg mnie już boli, więc oto co na razie nie działa:\n" +
                "- FIXED: 'Otwórz' nie ma funkcji\n" +
                "- 'Nowy' nie ma funkcji\n" +
                "- Kółko może wyjść poza obszar rysowania\n" +
                "- FIXED: Nie można zapisać projektu\n" +
                "- FIXED: Nie da się pokolorować wielokąta, po stworzeniu nowego\n" +
                "- FIXED: Nie da się usunąć wielokąta, po stworzeniu nowego\n" +
                "- FIXED: Nie da się przesuwać wielokąta\n" +
                "- FIXED: Nie da się skalować wielokąta\n" +
                "- Regulatory nie podążają, za przesuniętą figurą (easyfix)\n" +
                // Solucja ^, usunąć całkowicie, zamiast schować regulatory
                // Ponownie odpalić loopa na regulatory na żądanie
                "- Nie ma funkcji ołówka (wymaga myślenia)\n" +
                "- FIXED: Regulatory nie znikają po utworzeniu nowego prostokąta\n");
        String s ="Stworzył Marcel Jerzyk // 23.04.2018";
        alert.setContentText(s);
        alert.show();
    }

    @FXML
    void oProgramieKlik() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("O Programie");
        alert.setHeaderText("Prosty 'Paint' do tworzenia teledysku dla utworu trójkąty i kwadraty.\n" +
                "Tak na prawdę to nie, bo koła i prostokąty, a takiego utworu nie ma.\n" +
                "                               Czyli program jest do niczego.\n" +
                "                                            Miłej zabawy!");
        String s ="Stworzył Marcel Jerzyk // 23.04.2018";
        alert.setContentText(s);
        alert.show();
    }

    @FXML
    void wyjdzKlik() {
        Runtime.getRuntime().exit(0);
    }

    // XxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXx
    // Po kliknięciu buttona zapamiętuje wybrane narzędzie

    private int narzedzie = 0;

    @FXML
    void zaznaczenieKlik() {
        narzedzie = 0;
        System.out.println("Narzędzie (zaznaczenie): " + narzedzie);
    }

    @FXML
    void olowekKlik() {
        narzedzie = 1;
        System.out.println("Narzędzie (olowek): " + narzedzie);
    }

    @FXML
    void prostokatKlik() {
        narzedzie = 2;
        System.out.println("Narzędzie (prostokąt): " + narzedzie);
    }

    @FXML
    void koloKlik() {
        narzedzie = 3;
        System.out.println("Narzędzie (koło): " + narzedzie);
    }

    @FXML
    void wielokatKlik() {
        narzedzie = 4;
        System.out.println("Narzędzie (wielokąt): " + narzedzie);
    }

    @FXML
    void usunKlik() {
        narzedzie = 5;
        System.out.println("Narzędzie (czyszczenie): " + narzedzie);
    }

    @FXML
    void kolorujKlik() {
        narzedzie = 6;
        System.out.println("Narzędzie (kolorowanie): " + narzedzie);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Wybór Narzędzia | uaktywnia się przy kliknięciu na tuRysuje

    // Debug
    private int prostokatIndeks = 0;
    private int koloIndeks = 0;
    private int wielokatIndeks = 0;

    private boolean stworzony = false;
    private ArrayList<Double> punktyArrayowe = new ArrayList<>();

    private void WyborRysowanejFigury(int narzedzie) {

        switch (narzedzie) {
            case 0:
                break;

            case 1:
                break;

            case 2:
                prostokatIndeks++;
                System.out.println("Tworzę prostokąt #" + prostokatIndeks);
                Rectangle rect = stworzProstokat(koncowyX, koncowyY, 100, 100, prostokatIndeks);
                rect.setFill(wyborKoloru.getValue());
                tuRysuje.getChildren().add(rect);
                break;

            case 3:
                koloIndeks++;
                System.out.println("Tworzę koło #" + koloIndeks);
                Circle kolo = stworzKolo(koncowyX, koncowyY, 50, koloIndeks);
                kolo.setFill(wyborKoloru.getValue());
                tuRysuje.getChildren().add(kolo);
                break;

            case 4:
                tuRysuje.setOnMouseClicked((MouseEvent ep) -> {
                    if (ep.getButton() == MouseButton.PRIMARY) {
                        if (!stworzony) {
                            punktyArrayowe.clear();
                            punktyArrayowe.add(ep.getX());
                            punktyArrayowe.add(ep.getY());
                            stworzony = true;
                        } else {
                            punktyArrayowe.add(ep.getX());
                            punktyArrayowe.add(ep.getY());
                        }
                    } else {
                        wielokatIndeks++;
                        System.out.println("Tworzę wielokąt #" + wielokatIndeks);
                        double[] punkty = punktyArrayowe.stream().mapToDouble(d -> d).toArray();
                        Polygon wielokat = stworzWielokat(punkty, wielokatIndeks);
                        wielokat.setStroke(wyborKoloru.getValue());
                        wielokat.setFill(wyborKoloru.getValue());
                        stworzony = false;
                    }
                });
                break;

            case 5:
                break;

            case 6:
                break;
        }
    }

    // XxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXx
    // Wielokąt

    private Polygon stworzWielokat(double[] wielokatowe, int wielokatIndeks) {

        // Tworze wielokąt

        Polygon wielokat = new Polygon(wielokatowe);
        wielokat.setFill(wyborKoloru.getValue());

        // Printuje sb do debugu jego koordynaty
        // System.out.println(wielokat.getPoints());

        tuRysuje.getChildren().addAll(wielokat);

        // Loop w celu stworzenia regulatora dla każdego rogu

        Circle[] regulator = new Circle[40];
        int i = 0;
        for(i = 0; i < wielokat.getPoints().size(); i += 2 ){

            // Zbieram wartość X i Y, tworzę regulator

            regulator[i] = new Circle(wielokat.getPoints().get(i), wielokat.getPoints().get(i + 1), 3);
            regulator[i].setFill(Color.web("PERU", 0.7));
            regulator[i].setStroke(Color.PERU);
            regulator[i].setStrokeWidth(2);

            AtomicInteger polyCoordinateIndex = new AtomicInteger(i);

            // X

            regulator[i].centerXProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    wielokat.getPoints().set(polyCoordinateIndex.get(), newValue.doubleValue());
                }
            });

            // Y

            regulator[i].centerYProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    wielokat.getPoints().set(polyCoordinateIndex.get() + 1, (Double) newValue);
                }
            });
            setDragHandler(regulator[i]);
            tuRysuje.getChildren().add(regulator[i]);
        }

        // Do przesuwania

        final ObjectProperty<Point2D> mousePosition = new SimpleObjectProperty<>();

        wielokat.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
            }
        });

        wielokat.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double deltaX = event.getSceneX() - mousePosition.get().getX();
                double deltaY = event.getSceneY() - mousePosition.get().getY();
                wielokat.setLayoutX(wielokat.getLayoutX()+deltaX);
                wielokat.setLayoutY(wielokat.getLayoutY()+deltaY);
                mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
            }
        });

        // Chowanie i pokazywanie regulatorów

        int finalI = i;
        wielokat.setOnMouseEntered(event -> {
            if(narzedzie != 0) {
                for(int a = 0; a < finalI; a += 2)
                    tuRysuje.getChildren().remove(regulator[a]);
            }
        });

        wielokat.setOnMouseClicked(event -> {
            if(narzedzie == 6) {
                System.out.println("Koloruje wielokąt #" + wielokatIndeks);
                wielokat.setFill(wyborKoloru.getValue());
                wielokat.setStroke(wyborKoloru.getValue());
            }
            if(narzedzie == 5){
                tuRysuje.getChildren().remove(wielokat);
            }
            if(narzedzie == 0){
                if(!tuRysuje.getChildren().contains(regulator[0])) {
                    for (int a = 0; a < finalI; a += 2)
                        tuRysuje.getChildren().add(regulator[a]);
                }
            }
            System.out.println("Kliknąłeś wielokąt #" + wielokatIndeks);
        });

        tuRysuje.setOnMouseClicked(e -> WyborRysowanejFigury(narzedzie));

        return wielokat;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Prostokąt

    private Rectangle stworzProstokat(double x, double y, double szerokosc, double wysokosc, int prostokatIndeks) {

        final double dlugoscRegulatora = 5;

        Rectangle rect = new Rectangle(x, y, szerokosc, wysokosc);

        // Regulator po lewej na górze

        Rectangle regulatorNW = new Rectangle(dlugoscRegulatora, dlugoscRegulatora);

        regulatorNW.xProperty().bind(rect.xProperty().add(-dlugoscRegulatora));
        regulatorNW.yProperty().bind(rect.yProperty().add(-dlugoscRegulatora));

        // Regulator po prawej na dole:

        Rectangle regulatorSE = new Rectangle(dlugoscRegulatora, dlugoscRegulatora);

        regulatorSE.xProperty().bind(rect.xProperty().add(rect.widthProperty()));
        regulatorSE.yProperty().bind(rect.yProperty().add(rect.heightProperty()));

        // Zmuszenie regulatorów do posiadania prostokąta jako rodzica

        rect.parentProperty().addListener((obs, oldParent, newParent) -> {
            for (Rectangle c : Arrays.asList(regulatorNW, regulatorSE)) {
                Pane currentParent = (Pane) c.getParent();
                if (currentParent != null) {
                    currentParent.getChildren().remove(c);
                }
                ((Pane) newParent).getChildren().add(c);
            }
        });

        Wrapper<Point2D> mouseLocation = new Wrapper<>();

        // Przypisanie funkcji do regulatorów

        setUpDraggingProstokat(regulatorNW, mouseLocation);
        setUpDraggingProstokat(regulatorSE, mouseLocation);
        setUpDraggingProstokat(rect, mouseLocation);

        // Funkcje Regulatorów

        regulatorNW.setOnMouseDragged(event -> {
            if (mouseLocation.value != null) {
                double deltaX = event.getSceneX() - mouseLocation.value.getX();
                double deltaY = event.getSceneY() - mouseLocation.value.getY();
                double newX = rect.getX() + deltaX;
                double newY = rect.getY() + deltaY;

                if (newX >= dlugoscRegulatora && newX <= rect.getX() + rect.getWidth() - dlugoscRegulatora) {
                    rect.setX(newX);
                    rect.setWidth(rect.getWidth() - deltaX);
                }

                if (newY >= dlugoscRegulatora && newY <= rect.getY() + rect.getHeight() - dlugoscRegulatora) {
                    rect.setY(newY);
                    rect.setHeight(rect.getHeight() - deltaY);
                }

                mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
            }
        });

        regulatorSE.setOnMouseDragged(event -> {
            if (mouseLocation.value != null) {
                double deltaX = event.getSceneX() - mouseLocation.value.getX();
                double deltaY = event.getSceneY() - mouseLocation.value.getY();
                double newMaxX = rect.getX() + rect.getWidth() + deltaX;
                double newMaxY = rect.getY() + rect.getHeight() + deltaY;

                if (newMaxX >= rect.getX() && newMaxX <= rect.getParent().getBoundsInLocal().getWidth() - dlugoscRegulatora) {
                    rect.setWidth(rect.getWidth() + deltaX);
                }

                if (newMaxY >= rect.getY() && newMaxY <= rect.getParent().getBoundsInLocal().getHeight() - dlugoscRegulatora) {
                    rect.setHeight(rect.getHeight() + deltaY);
                }

                mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
            }
        });

        // Przemieszczanie Figury

        rect.setOnMouseDragged(event -> {
            if (mouseLocation.value != null) {
                double deltaX = event.getSceneX() - mouseLocation.value.getX();
                double deltaY = event.getSceneY() - mouseLocation.value.getY();
                double newX = rect.getX() + deltaX;
                double newMaxX = newX + rect.getWidth();
                double newY = rect.getY() + deltaY;
                double newMaxY = newY + rect.getHeight();

                if (newX >= dlugoscRegulatora
                        && newMaxX <= rect.getParent().getBoundsInLocal().getWidth() - dlugoscRegulatora) {
                    rect.setX(newX);
                }

                if (newY >= dlugoscRegulatora
                        && newMaxY <= rect.getParent().getBoundsInLocal().getHeight() - dlugoscRegulatora) {
                    rect.setY(newY);
                }

                mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
            }
        });

        // Zmiana koloru oraz znikanie regulatorów, gdy nie są potrzebne

        rect.setOnMouseClicked(event -> {
            if(narzedzie == 6) {
                rect.setFill(wyborKoloru.getValue());
            }
            if(narzedzie == 5){
                tuRysuje.getChildren().remove(regulatorNW);
                tuRysuje.getChildren().remove(regulatorSE);
                tuRysuje.getChildren().remove(rect);
            }
            if(narzedzie == 0){
                if(!tuRysuje.getChildren().contains(regulatorNW)){
                    tuRysuje.getChildren().add(regulatorNW);
                    tuRysuje.getChildren().add(regulatorSE);
                }
            }
            System.out.println("Kliknąłeś prostokąt #" + prostokatIndeks);
        });

        rect.setOnMouseEntered(event -> {
            if(narzedzie != 0) {
                tuRysuje.getChildren().remove(regulatorNW);
                tuRysuje.getChildren().remove(regulatorSE);
            }
        });
        return rect;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Koło

    private Circle stworzKolo(double x, double y, double srednica, int koloIndeks) {

        final double dlugoscRegulatora = 3;

        Circle kolo = new Circle(x, y, srednica);

        // Regulator na środku:

        Circle regulatorMID = new Circle(dlugoscRegulatora);
        regulatorMID.setFill(Color.web("PERU", 0.7));
        regulatorMID.setStroke(Color.PERU);
        regulatorMID.setStrokeWidth(2);

        regulatorMID.centerXProperty().bind(kolo.centerXProperty());
        regulatorMID.centerYProperty().bind(kolo.centerYProperty());

        // Zmuszenie regulatora do posiadania koła jako rodzica

        kolo.parentProperty().addListener((obs, oldParent, newParent) -> {
            for (Circle c : Collections.singletonList(regulatorMID)) {
                Pane currentParent = (Pane) c.getParent();
                if (currentParent != null) {
                    currentParent.getChildren().remove(c);
                }
                ((Pane) newParent).getChildren().add(c);
            }
        });

        Wrapper<Point2D> mouseLocation = new Wrapper<>();

        // Przypisanie funkcji do regulatora

        setUpDraggingKolo(regulatorMID, mouseLocation);
        setUpDraggingKolo(kolo, mouseLocation);

        // Funkcje Regulatorów

        regulatorMID.setOnMouseDragged(event -> {
            if (mouseLocation.value != null) {
                double deltaX = event.getSceneX() - mouseLocation.value.getX();
                double deltaY = event.getSceneY() - mouseLocation.value.getY();
                double newMaxX = kolo.getCenterX() + kolo.getRadius() + deltaX;
                double newMaxY = kolo.getCenterY() + kolo.getRadius() + deltaY;

                if (newMaxX >= kolo.getCenterX() && newMaxX <= kolo.getParent().getBoundsInLocal().getWidth() - dlugoscRegulatora) {
                    kolo.setRadius(kolo.getRadius() + deltaX);
                }

                if (newMaxY >= kolo.getCenterY() && newMaxY <= kolo.getParent().getBoundsInLocal().getHeight() - dlugoscRegulatora) {
                    kolo.setRadius(kolo.getRadius() + deltaY);
                }

                mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
            }
        });

        // Przemieszczanie Figury

        kolo.setOnMouseDragged(event -> {
            if (mouseLocation.value != null) {
                double deltaX = event.getSceneX() - mouseLocation.value.getX();
                double deltaY = event.getSceneY() - mouseLocation.value.getY();
                double newX = kolo.getCenterX() + deltaX;
                double newMaxX = newX + kolo.getRadius();
                double newY = kolo.getCenterY() + deltaY;
                double newMaxY = newY + kolo.getRadius();

                if (newX >= dlugoscRegulatora
                        && newMaxX <= kolo.getParent().getBoundsInLocal().getWidth() - dlugoscRegulatora) {
                    kolo.setCenterX(newX);
                }

                if (newY >= dlugoscRegulatora
                        && newMaxY <= kolo.getParent().getBoundsInLocal().getHeight() - dlugoscRegulatora) {
                    kolo.setCenterY(newY);
                }

                mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
            }

        });

        // Zmiana koloru oraz znikanie regulatora, gdy nie jest potrzebny

        kolo.setOnMouseClicked(event -> {
            if(narzedzie == 6) {
                kolo.setFill(wyborKoloru.getValue());
            }
            if(narzedzie == 5){
                tuRysuje.getChildren().remove(regulatorMID);
                tuRysuje.getChildren().remove(kolo);
            }
            if(narzedzie == 0){
                if(!tuRysuje.getChildren().contains(regulatorMID)){
                    tuRysuje.getChildren().add(regulatorMID);
                }
            }
            System.out.println("Kliknąłeś koło #" + koloIndeks);
        });

        kolo.setOnMouseEntered(event -> {
            if(narzedzie != 0) {
                tuRysuje.getChildren().remove(regulatorMID);
            }
        });

        return kolo;
    }

    // XxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXx

    private void setUpDraggingProstokat(Rectangle rectangle, Wrapper<Point2D> mouseLocation) {

        rectangle.setOnDragDetected(event -> {
            rectangle.getParent().setCursor(Cursor.CLOSED_HAND);
            mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
        });

        rectangle.setOnMouseReleased(event -> {
            rectangle.getParent().setCursor(Cursor.DEFAULT);
            mouseLocation.value = null;
        });
    }

    private void setUpDraggingKolo(Circle circle, Wrapper<Point2D> mouseLocation) {

        circle.setOnDragDetected(event -> {
            circle.getParent().setCursor(Cursor.CLOSED_HAND);
            mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
        });

        circle.setOnMouseReleased(event -> {
            circle.getParent().setCursor(Cursor.DEFAULT);
            mouseLocation.value = null ;
        });
    }

    static class Wrapper<T> {
        T value;
    }

    // XxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXx

    private void zgrajFotkeiZapisz (){
        FileChooser wyborPliku = new FileChooser();

        // Stworzenie wyboru formatu pliku
        wyborPliku.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG to najlepszy format, byku (*.png)", "*.png"));

        // Niech użytkownik wybierze plik
        File file = wyborPliku.showSaveDialog(null);

        if(file != null){
            try {

                // Screenuj Pane z Rysunkiem

                WritableImage rysuneczek = new WritableImage((int)tuRysuje.getWidth() + 20,
                        (int)tuRysuje.getHeight() + 20);
                tuRysuje.snapshot(null, rysuneczek);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(rysuneczek, null);

                // Wrzuć screena w plik

                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) { ex.printStackTrace(); }
        }
    }

    public void wczytajFotke() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            wczytanyObrazeczek.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(PaintKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // XxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXx

    private double dragDeltaX, dragDeltaY;

    private void setDragHandler( Circle circle )
    {
        circle.setOnMousePressed( new EventHandler<MouseEvent>() {
            @Override public void handle( MouseEvent mouseEvent ) {
                dragDeltaX = circle.getCenterX() - mouseEvent.getSceneX();
                dragDeltaY = circle.getCenterY() - mouseEvent.getSceneY();
            }
        } );

        circle.setOnMouseDragged( new EventHandler<MouseEvent>() {
            @Override public void handle( MouseEvent mouseEvent ) {
                circle.setCenterX( mouseEvent.getSceneX() + dragDeltaX );
                circle.setCenterY( mouseEvent.getSceneY() + dragDeltaY );
                circle.setCursor( Cursor.MOVE );
            }
        } );

        circle.setOnMouseEntered( new EventHandler<MouseEvent>() {
            @Override public void handle( MouseEvent mouseEvent ) {
                circle.setCursor( Cursor.HAND );
            }
        } );

        circle.setOnMouseReleased( new EventHandler<MouseEvent>() {
            @Override public void handle( MouseEvent mouseEvent ) {
                circle.setCursor( Cursor.HAND );
            }
        } );
    }
}
// Skróty klawiszowe (potem)
        /*
        caleOkno.setOnKeyPressed(ke -> {
            KeyCode klawiaturkaInput = ke.getCode();
            if (klawiaturkaInput.equals(KeyCode.DELETE)) {
                tuRysuje.getChildren().remove(regulatorNW);
                tuRysuje.getChildren().remove(regulatorSE);
                tuRysuje.getChildren().remove(rect);
            }
        });*/