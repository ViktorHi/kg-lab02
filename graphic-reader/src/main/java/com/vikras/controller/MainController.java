package com.vikras.controller;

import com.vikras.model.Engine;
import com.vikras.model.processor.FileProvider;
import com.vikras.model.processor.ImageAnalyzer;
import com.vikras.model.processor.ImageInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MainController {

    @FXML
    private Button selectBt;

    @FXML
    private Label selectLb;

    @FXML
    private TableView<ImageInfo> tableView;

    @FXML
    private TableColumn<?, ?> fileNameTC;

    @FXML
    private TableColumn<?, ?> widthTC;

    @FXML
    private TableColumn<?, ?> heightTC;

    @FXML
    private TableColumn<?, ?> widthDpiTC;

    @FXML
    private TableColumn<?, ?> heightDpiTC;

    @FXML
    private TableColumn<?, ?> colorDpiTC;

    @FXML
    private TableColumn<?, ?> compressionTC;

    @FXML
    private Label infoLabel;


    private Stage stage;
    private DialogChoosers choosers;
    private Engine engine;

    public void init(Stage stage){
        this.stage = stage;
        engine = new Engine();
        choosers = new DialogChoosers(ImageAnalyzer.getSupportedExtensionList());
        selectBt.setOnAction(this::selectBtActionHandler);
        initTable();
    }

    private void selectBtActionHandler(ActionEvent actionEvent) {
        Optional<File> file = choosers.showDirectoryDialog(stage);
        file.ifPresent(e -> {
            updateByPath(e.toPath());
            selectLb.setText("Selected: "+ e.toString());
        });
    }

    private void updateByPath(Path path) {
        try {
            Instant start = Instant.now();
            var imageInfo = engine.getImageInfo(path);
            Instant end = Instant.now();
            long seconds = Duration.between(start, end).getSeconds();
            long nanos = Duration.between(start, end).getNano();
            updateTable(imageInfo);
            var directorySize = FileProvider.getDirectorySize(path.toFile()) /1000000.0;
//            var directorySize = FileProvider.getDirectorySize(path.toFile()) /1048576.0;

            var text ="";
            if(seconds != 0){
                text = "Processed in: " + seconds + " seconds";
            }else{
                text = "Processed in: " + TimeUnit.NANOSECONDS.toMillis(nanos) + " milliseconds";
            }
            setInfo(text+ "     size: "+ String.format("%.3fMB", directorySize) + "     files: " + imageInfo.size());
        } catch (Exception e) {
            setInfo(e.getMessage());
        }
    }


    private void initTable() {
        fileNameTC.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        widthDpiTC.setCellValueFactory(new PropertyValueFactory<>("dpiW"));
        heightDpiTC.setCellValueFactory(new PropertyValueFactory<>("dpiH"));
        widthTC.setCellValueFactory(new PropertyValueFactory<>("width"));
        heightTC.setCellValueFactory(new PropertyValueFactory<>("height"));
        colorDpiTC.setCellValueFactory(new PropertyValueFactory<>("colorDepth"));
        compressionTC.setCellValueFactory(new PropertyValueFactory<>("zipping"));
    }

    private void updateTable(List<ImageInfo> infos) {
        ObservableList<ImageInfo> list = getTableValues(infos);
        tableView.setItems(list);
    }

    private ObservableList<ImageInfo> getTableValues(List<ImageInfo> appliances) {
        return appliances.stream()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    void setInfo(String text){
        infoLabel.setText(text);
    }


}

