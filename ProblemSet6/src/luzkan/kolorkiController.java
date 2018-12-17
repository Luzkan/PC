//Marcel Jerzyk pierdolnął programik 10.05.2018 15:56 sign out

package luzkan;

import javafx.animation.PauseTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class kolorkiController {

    // XxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXx
    // Menu functions and descriptions

    @FXML
    void saveAsClick() {
        screenshotAndSave();
    }

    @FXML
    void saveClick() {
        screenshotAndSave();
    }

    @FXML
    void helpClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Frequency is in milliseconds\n" +
                "Program tries to change the color in random time section\n" +
                "Random section is equal to 0.5 up to 1.5 of the frequency\n" +
                "---------------------------------------\n" +
                "TWO MODES:\n" +
                "All At Once: changes all colors at once depending on probability\n" +
                "Separately: changes all colors separately depending on probability\n" +
                "---------------------------------------\n" +
                "Some are taking colors of the surroundings\n" +
                "Some are generating new random color");
        alert.show();
    }

    @FXML
    void featuresClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Features");
        alert.setHeaderText("Program Features:\n" +
                "- Randomly generated first few colors\n" +
                "- Colors based on the surroundings\n" +
                "- Randomly chosen time between generation of colors\n" +
                "- Controller for the average time between generations\n" +
                "- Controller for the probability of change\n" +
                "- Exception handler\n" +
                "- Resizable grid\n");
        alert.setContentText("Additional features:\n" +
                "- Screen capture of current grid\n" +
                "- Dynamic Probability\n" +
                "- Two Modes (all at once and all separately)\n" +
                "- Clear function\n" +
                "- Stop function\n" +
                "- Block Size function\n" +
                "- Menu bar\n" +
                "- Help/About/Bugs/Features/Close\n" +
                "- Close button");
        alert.show();
    }

    @FXML
    void bugsClick() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Bugs");
        alert.setHeaderText("Known problems:\n" +
                "- Frequency Slider is not dynamic");
        alert.setContentText("Last Update: 10.05.2018 // 15:21");
        alert.show();
    }

    @FXML
    void aboutClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Program made for College Project");
        String s = "Made by Marcel Jerzyk // 01.05.2018";
        alert.setContentText(s);
        alert.show();
    }

    @FXML
    void closeClick() {
        Runtime.getRuntime().exit(0);
    }

    // XxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXx
    // Pane and stuff creation, ActionEvents for values

    @FXML
    private Pane gridHere;

    @FXML
    private TextField sizeX;

    private int gridSizeX = 1;

    @FXML
    void valueX() {

        try{
            gridSizeX = Integer.parseInt(sizeX.getText());
            System.out.println("Grid size (x): " + sizeX.getText());
        } catch(NumberFormatException ex){
            System.err.println("Size X is not a number.");
            System.err.println("Grid size (x): " + sizeX.getText());
        }

    }

    @FXML
    private TextField sizeY;

    private int gridSizeY = 1;

    @FXML
    void valueY() {

        try{
            gridSizeY = Integer.parseInt(sizeY.getText());
            System.out.println("Grid size (y): " + sizeY.getText());
        } catch(NumberFormatException ex){
            System.err.println("Size Y is not a number.");
            System.err.println("Grid size (y): " + sizeY.getText());
        }

    }

    @FXML
    private Slider frequencySlider;

    @FXML
    private Slider probabilitySlider;

    @FXML
    private Text sliderValue;

    @FXML
    void sliderValueChangeFreq(MouseEvent event) {
        sliderValue.setText(String.valueOf(Math.round(frequencySlider.getValue())));
    }

    @FXML
    void sliderValueChangeProb(MouseEvent event) {
        sliderValue.setText(String.valueOf(Math.round(probabilitySlider.getValue())));
    }

    @FXML
    private TextField sizeBlock;

    private int blockSize = 1;

    @FXML
    void valueSize() {

        try{
            blockSize = Integer.parseInt(sizeBlock.getText());
            System.out.println("Blcok Size: " + sizeBlock.getText());
        } catch(NumberFormatException ex){
            System.err.println("Size Y is not a number.");
            System.err.println("Block Size: " + sizeBlock.getText());
        }

    }


    @FXML
    void initialize() {

        // Grid default color
        gridHere.setStyle("-fx-background-color: white;");

    }

    // XxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXx
    // Buttons to do stuff

    private boolean stop = false;
    private boolean clear = false;

    @FXML
    void allAtOnceClick() {
        stop = false;
        clear = false;
        Rectangle[] rectGrid = new Rectangle[1 + gridSizeX + gridSizeX*gridSizeY];

        //Grid creation
        //Default Parameters for block size
        int blockHeight = blockSize;
        int blockWidth = blockHeight;


        for(int nH = 1; nH <= gridSizeY; nH++) {
            for (int nW = 1; nW <= gridSizeX; nW++) {
                rectGrid[nW + ((nH-1)*gridSizeX)] = createGrid((nW * blockWidth) - blockWidth, (nH * blockHeight) - blockHeight, blockWidth, blockHeight, nW, nH);
            }
        }
        System.out.println("Created grid [" + gridSizeX + "x" + gridSizeY +"].");
        System.out.println("Last block ID:" + (gridSizeX*gridSizeY));

        PauseTransition waitFrequency = new PauseTransition(Duration.millis((0.5+Math.random())*frequencySlider.getValue()));
        waitFrequency.setOnFinished((e) -> {

            // Loop for all rectangles
            for(int nH = 1; nH <= gridSizeY; nH++) {
                for (int nW = 1; nW <= gridSizeX; nW++) {

                    double randomNumber = Math.random();

                    //Probability to change color
                    if(randomNumber <= (probabilitySlider.getValue()/100)){
                        rectGrid[nW + ((nH-1)*gridSizeX)].setFill((Color.color(Math.random(), Math.random(), Math.random())));
                        System.out.println("Random roll - changing color of " + (nW + ((nH-1)*gridSizeX)));

                        //Probability to take average color
                    }else if(randomNumber <= (1-(probabilitySlider.getValue()/100))){

                        // Detecting ID of surroundings if they are present, fetching their RGB
                        int center = (nW + ((nH-1)*gridSizeX));

                        double lR = 0, lG = 0, lB = 0, rR = 0, rG = 0, rB = 0, bR = 0, bG = 0, bB = 0, aR = 0, aG = 0, aB = 0, added = 0;

                        if((center-1) % gridSizeX != 0 && (center-1) != 0){
                            int left = (center - 1);
                            Color leftColor = (Color) rectGrid[left].getFill();
                            lR = leftColor.getRed();
                            lG = leftColor.getGreen();
                            lB = leftColor.getBlue();
                            added++;
                        }

                        if(center % gridSizeX != 0 && center != gridSizeX*gridSizeY){
                            int right = (center + 1);
                            Color rightColor = (Color) rectGrid[right].getFill();
                            rR = rightColor.getRed();
                            rG = rightColor.getGreen();
                            rB = rightColor.getBlue();
                            added++;
                        }

                        if(center-gridSizeX > 0){
                            int below = (center - gridSizeX);
                            Color belowColor = (Color) rectGrid[below].getFill();
                            bR = belowColor.getRed();
                            bG = belowColor.getGreen();
                            bB = belowColor.getBlue();
                            added++;
                        }

                        if(center+gridSizeX < gridSizeX*gridSizeY){
                            int above = (center + gridSizeX);
                            Color aboveColor = (Color) rectGrid[above].getFill();
                            aR = aboveColor.getRed();
                            aG = aboveColor.getGreen();
                            aB = aboveColor.getBlue();
                            added++;
                        }

                        double redAverage = (lR + rR + bR + aR)/added;
                        double greenAverage = (lG + rG + bG + aG)/added;
                        double blueAverage = (lB + rB + bB + aB)/added;

                        rectGrid[nW + ((nH-1)*gridSizeX)].setFill((Color.color(redAverage, greenAverage, blueAverage)));
                        System.out.println("Average roll - changing color of " + (nW + ((nH-1)*gridSizeX)));

                    }else{
                        System.out.println("Negative roll - doing nothing with " + (nW + ((nH-1)*gridSizeX)));
                    }
                }
            }
            System.out.println("Freq Slider: " + frequencySlider.getValue()  + "ms.");
            System.out.println("Prob Slider: " + probabilitySlider.getValue() + "%.");
            System.out.println("Actual time: " + waitFrequency.getDuration());

            System.out.println("Loop Stopped? - " + stop);


            if(clear){
                Rectangle cover = new Rectangle(0,  0, gridHere.getWidth(), gridHere.getHeight());
                cover.setFill(Color.WHITE);
                gridHere.getChildren().add(cover);
            }

            if(stop){
                return;
            }

            waitFrequency.playFromStart();
        });
        waitFrequency.play();
    }

    // Unbinded means separately but I'm english word definitions creationist

    @FXML
    void unbindedClick() {
        stop = false;
        clear = false;
        Rectangle[] rectGrid = new Rectangle[1 + gridSizeX + gridSizeX*gridSizeY];

        //Grid creation
        //Default Parameters for block size
        int blockHeight = blockSize;
        int blockWidth = blockHeight;


        for(int nH = 1; nH <= gridSizeY; nH++) {
            for (int nW = 1; nW <= gridSizeX; nW++) {
                rectGrid[nW + ((nH-1)*gridSizeX)] = createGrid((nW * blockWidth) - blockWidth, (nH * blockHeight) - blockHeight, blockWidth, blockHeight, nW, nH);
            }
        }
        System.out.println("Created grid [" + gridSizeX + "x" + gridSizeY +"].");
        System.out.println("Last block ID:" + (gridSizeX*gridSizeY));

        // Loop for all rectangles
        for(int nH = 1; nH <= gridSizeY; nH++) {
            for (int nW = 1; nW <= gridSizeX; nW++) {

                PauseTransition waitFrequency = new PauseTransition(Duration.millis((0.5+Math.random())*frequencySlider.getValue()));
                int finalNW = nW;
                int finalNH = nH;
                waitFrequency.setOnFinished((e) -> {

                double randomNumber = Math.random();

                //Probability to change color
                if(randomNumber <= (probabilitySlider.getValue()/100)){
                    rectGrid[finalNW + ((finalNH -1)*gridSizeX)].setFill((Color.color(Math.random(), Math.random(), Math.random())));
                    System.out.println("Random roll - changing color of " + (finalNW + ((finalNH -1)*gridSizeX)));

                    //Probability to take average color
                }else if(randomNumber <= (1-(probabilitySlider.getValue()/100))){

                    // Detecting ID of surroundings if they are present, fetching their RGB
                    int center = (finalNW + ((finalNH -1)*gridSizeX));

                    double lR = 0, lG = 0, lB = 0, rR = 0, rG = 0, rB = 0, bR = 0, bG = 0, bB = 0, aR = 0, aG = 0, aB = 0, added = 0;

                    if((center-1) % gridSizeX != 0 && (center-1) != 0){
                        int left = (center - 1);
                        Color leftColor = (Color) rectGrid[left].getFill();
                        lR = leftColor.getRed();
                        lG = leftColor.getGreen();
                        lB = leftColor.getBlue();
                        added++;
                    }

                    if(center % gridSizeX != 0 && center != gridSizeX*gridSizeY){
                        int right = (center + 1);
                        Color rightColor = (Color) rectGrid[right].getFill();
                        rR = rightColor.getRed();
                        rG = rightColor.getGreen();
                        rB = rightColor.getBlue();
                        added++;
                    }

                    if(center-gridSizeX > 0){
                        int below = (center - gridSizeX);
                        Color belowColor = (Color) rectGrid[below].getFill();
                        bR = belowColor.getRed();
                        bG = belowColor.getGreen();
                        bB = belowColor.getBlue();
                        added++;
                    }

                    if(center+gridSizeX < gridSizeX*gridSizeY){
                        int above = (center + gridSizeX);
                        Color aboveColor = (Color) rectGrid[above].getFill();
                        aR = aboveColor.getRed();
                        aG = aboveColor.getGreen();
                        aB = aboveColor.getBlue();
                        added++;
                    }

                    double redAverage = (lR + rR + bR + aR)/added;
                    double greenAverage = (lG + rG + bG + aG)/added;
                    double blueAverage = (lB + rB + bB + aB)/added;

                    rectGrid[finalNW + ((finalNH -1)*gridSizeX)].setFill((Color.color(redAverage, greenAverage, blueAverage)));
                    System.out.println("Average roll - changing color of " + (finalNW + ((finalNH -1)*gridSizeX)));

                }else{
                    System.out.println("Negative roll - doing nothing with " + (finalNW + ((finalNH -1)*gridSizeX)));
                }
                    System.out.println("Freq Slider: " + frequencySlider.getValue()  + "ms.");
                    System.out.println("Prob Slider: " + probabilitySlider.getValue() + "%.");
                    System.out.println("Actual time: " + waitFrequency.getDuration());

                    if(clear){
                        Rectangle cover = new Rectangle(0,  0, gridHere.getWidth(), gridHere.getHeight());
                        cover.setFill(Color.WHITE);
                        gridHere.getChildren().add(cover);
                    }

                    if(stop){
                        return;
                    }

                    waitFrequency.playFromStart();

                });
                waitFrequency.play();
            }
        }
    }

    @FXML
    void stopClick() {
        stop = true;
    }

    @FXML
    void stopclearClick() {
        clear = true;
        stop = true;
    }


    // XxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXx
    // Creating the grid

    private Rectangle createGrid(int x, int y, int width, int height, int nW, int nH) {

        Rectangle[] smallBlock = new Rectangle[1 + gridSizeX + gridSizeX*gridSizeY];
        smallBlock[nW + ((nH-1)*gridSizeX)] = new Rectangle(x, y, width, height);

        smallBlock[nW + ((nH-1)*gridSizeX)].setFill((Color.color(Math.random(), Math.random(), Math.random())));
        gridHere.getChildren().add(smallBlock[nW + ((nH-1)*gridSizeX)]);

        //Check ID on Click - just for debugging
        smallBlock[nW + ((nH-1)*gridSizeX)].setOnMouseClicked(event -> System.out.println("You clicked block #" + (nW + ((nH-1)*gridSizeX))));

        return smallBlock[nW + ((nH-1)*gridSizeX)];
    }


    // XxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXx
    // Screen Capture feature

    private void screenshotAndSave() {
        FileChooser chooseFile = new FileChooser();

        // Creating file format chooser
        chooseFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG superior extension (*.png)", "*.png"));

        // Letting user choose the file
        File file = chooseFile.showSaveDialog(null);

        if (file != null) {
            try {

                // Screenshot Pane with the grid
                WritableImage gridScreenshot = new WritableImage((int) gridHere.getWidth() + 20,
                        (int) gridHere.getHeight() + 20);
                gridHere.snapshot(null, gridScreenshot);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(gridScreenshot, null);

                // Throw screenshot into the file
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

/*
wholeWindow.setOnKeyPressed(k -> {
            KeyCode klawiaturkaInput = k.getCode();
            if (klawiaturkaInput.equals(KeyCode.ENTER)) {
 */