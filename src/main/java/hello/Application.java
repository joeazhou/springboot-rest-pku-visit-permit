package hello;

import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public void printHeaders(HttpHeaders hds) {
		log.info(hds.toString());
	}
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			long count = 0;
			while (count < 6 * 24 * 10) {
				count++;
				final String url = "https://visitor.pku.edu.cn/visitor/ctrl/tourist/tourist/queryDaysAhead2.do";
				final String forcereadflagurl = "https://visitor.pku.edu.cn/visitor/ctrl/tourist/tourist/forceReadFlag.do";
				final String queryopenflagurl = "https://visitor.pku.edu.cn/visitor/ctrl/tourist/tourist/queryOpenFlag.do";
				String temp_cookie = "JSESSIONID=A6D76FD86CE178D37D93757E2ECAA8D4";
				RestTemplate template = new RestTemplate();

				HttpHeaders headers = new HttpHeaders();

				HttpEntity<String> response = template.exchange(forcereadflagurl, HttpMethod.GET,
						new HttpEntity<String>(headers), String.class);
				HttpHeaders resheaders = response.getHeaders();

//				log.info("url: " + forcereadflagurl);
//				log.info(response.toString());
//				log.info("response header:" + resheaders.SET_COOKIE);
//				log.info("response header:" + resheaders.SET_COOKIE2);
				String set_cookie = resheaders.getFirst(resheaders.SET_COOKIE);

				StringTokenizer st = new StringTokenizer(set_cookie, "; ");
				temp_cookie = st.nextToken();
//				log.info("Set-Cookie: " + temp_cookie );
				temp_cookie = st.nextToken();
//				log.info("Set-Cookie: " + temp_cookie );
				temp_cookie = st.nextToken();
//				log.info("Set-Cookie: " + temp_cookie );

				headers.add("Cookie", set_cookie);
				response = template.exchange(queryopenflagurl, HttpMethod.GET, new HttpEntity<String>(headers),
						String.class);
				resheaders = response.getHeaders();
//				log.info("url: " + queryopenflagurl);
//				log.info(response.toString());

				Thread.sleep(11000);
				
				headers = new HttpHeaders();
				headers.add("Cookie", set_cookie);
				headers.add("Connection", "Keep-Alive");
				headers.add("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
//				printHeaders(headers);
				response = template.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class);
				resheaders = response.getHeaders();
//				log.info(response.toString());
				// log.info(response.getBody());
				
				String jsonString = response.getBody();
				ObjectMapper mapper = new ObjectMapper();
				PKUvisitOK pvok = mapper.readValue(jsonString, PKUvisitOK.class);

				if (pvok.success == false) {
					log.info("Success:" + pvok.success);
					break;
				}
				long totalleft = 0;
				for (PKUvisitOK.Row onok : pvok.rows) {
					totalleft += onok.leftCount1 + onok.leftCount2;

					if (totalleft > 0)
						log.info(onok.date + " TOTAL LEFT: " + onok.leftCount + " IN THE MORNING: " + onok.leftCount1
								+ " IN THE AFTERNOON: " + onok.leftCount2);
				}
				if (count % 12 == 0)
					log.info("\n********* FINISHED ONE HOUR *******");
				else
					System.out.print(".");
				Thread.sleep(1000 * 60 * 5);
			}

		};
	}
}