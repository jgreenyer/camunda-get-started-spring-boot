package de.fhdw.praxisarbeit.tasks;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ArbeitBewerten implements JavaDelegate{

	static List<Integer> notenListe = Arrays.asList(100, 130, 170, 200, 230, 270, 300, 330, 370, 400, 500);
	static Random rand = new Random();

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Boolean fertig = (Boolean) execution.getVariable("fertig");
		execution.setVariable("note", noteFestlegen(fertig));
		execution.setVariable("gutachten", gutachtenSchreiben());
	}
	
	private int noteFestlegen(Boolean fertig) {
		if (fertig) {
			return notenListe.get(rand.nextInt(notenListe.size()));
		}else {
			return 500;
		}		
	}

	private String gutachtenSchreiben() throws Exception {
		return get("https://baconipsum.com/api/?type=meat-and-filler&paras=5&format=text").body();		
	}

    private HttpResponse<String> get(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        return response;
    }

}
