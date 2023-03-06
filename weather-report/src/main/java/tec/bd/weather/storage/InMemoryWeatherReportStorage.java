package tec.bd.weather.storage;

import tec.bd.weather.model.Report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InMemoryWeatherReportStorage implements WeatherReportStorage {

    private Map<String, Report> db;

    public InMemoryWeatherReportStorage() {
        this.db = new LinkedHashMap<>();
    }

    /*
     * Esto tiene que ser implementado
     *
     */

    @Override
    public void save(Report report){
        var reportKey = generateKeyFromReport(report);
        db.put(reportKey, report);
    }

    @Override
    public void remove(String reportKey) {
        db.remove(reportKey);
    }

    @Override
    public Report update(Report oldReport) {
        var oldReportKey = generateKeyFromReport(oldReport);
        var newUpdatedReport = db.get(oldReportKey);
        if (newUpdatedReport != null) {
            db.put(oldReportKey, newUpdatedReport);
        }
        return newUpdatedReport;
    }

    @Override
    public Report find(String reportKey) {
        return db.get(reportKey);
    }

    @Override
    public List<Report> find() {
        var reportList = new ArrayList<>(db.values());
        return reportList;
    }

    private String generateKeyFromReport(Report report) {
        var dateFormat = new SimpleDateFormat("dd-mm-YYYY");
        return (dateFormat.format(report.getDate()) + "-" + report.getReportType());
    }
}
