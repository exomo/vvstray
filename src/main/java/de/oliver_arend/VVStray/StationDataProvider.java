package de.oliver_arend.VVStray;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class StationDataProvider {

	private static final Gson gson = new Gson();
	private static ArrayList<Station> allStations;

	private StationDataProvider() { }

	private static void parseStationJson() {
		String stationJson = Utils.getTextFromResource("vvs_stops.json", StandardCharsets.UTF_8);
		Type stationListType = new TypeToken<ArrayList<Station>>(){}.getType();
		allStations = gson.fromJson(stationJson, stationListType);
	}

	public static ArrayList<Station> getStationsArrayList() {
		if(allStations == null) { parseStationJson(); }
		return allStations;
	}

	public static Station[] getStationsArray(String currentEntry) {
		if(allStations == null) { parseStationJson(); }
		Stream<Station> beginsWith = allStations.stream()
				.filter(s -> s.getName().startsWith(currentEntry))
				.sorted();
		Stream<Station> contains = allStations.stream()
				.filter(s -> s.getName().substring(1).contains(currentEntry))
				.sorted();
		return Stream.concat(beginsWith, contains)
				.toArray(Station[]::new);
	}
}
