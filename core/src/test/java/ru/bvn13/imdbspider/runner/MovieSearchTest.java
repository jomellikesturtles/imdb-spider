package ru.bvn13.imdbspider.runner;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.bvn13.imdbspider.ImdbSpider;
import ru.bvn13.imdbspider.exceptions.ImdbSpiderException;
import ru.bvn13.imdbspider.imdb.Movie;
import ru.bvn13.imdbspider.imdb.MovieDataType;
import ru.bvn13.imdbspider.imdb.MovieList;
import ru.bvn13.imdbspider.imdb.Tagline;
import ru.bvn13.imdbspider.imdb.accessories.SoundMix;

import static org.junit.Assert.*;


public class MovieSearchTest {

    private static final String TERMINATOR_STORYLINE = "A cyborg is sent from the future on a deadly mission. He has to kill Sarah Connor, a young woman whose life will have a great significance in years to come. Sarah has only one protector - Kyle Reese - also sent from the future. The Terminator uses his exceptional intelligence and strength to find Sarah, but is there any way to stop the seemingly indestructible cyborg ?";
    private static final String TERMINATOR_POSTER_LINK = "https://m.media-amazon.com/images/M/MV5BYTViNzMxZjEtZGEwNy00MDNiLWIzNGQtZDY2MjQ1OWViZjFmXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UX182_CR0,0,182,268_AL_.jpg";

    private static final String TERMINATOR_TAGLINE_1 = "I'll be back!";
    private static final String TERMINATOR_TAGLINE_2 = "In the Year of Darkness, 2029, the rulers of this planet devised the ultimate plan. They would reshape the Future by changing the Past. The plan required something that felt no pity. No pain. No fear. Something unstoppable. They created 'THE TERMINATOR'";
    private static final String TERMINATOR_TAGLINE_3 = "The thing that won't die, in the nightmare that won't end.";
    private static final String TERMINATOR_TAGLINE_4 = "Your future is in its hands.";
    private static final String TERMINATOR_TAGLINE_5 = "La sua missione e una sola: distruggere, uccidere... (His one and only mission: to destroy, to kill...) (Italian DVD)";

    private static ImdbSpider spider;

    @BeforeClass
    public static void initClass() {
        spider = ImdbSpider.withApi_1_0()
                .addHttpRequestHeader("Content-Language", "ru-RU");
    }

    @Test
    public void testSearchTerminator() throws ImdbSpiderException {
        MovieList result = spider.searchMovieByTitle("Терминатор", 5,
                MovieDataType.ID,
                MovieDataType.TITLE,
                MovieDataType.ORIGINAL_TITLE,
                MovieDataType.YEAR,
                MovieDataType.STORYLINE,
                MovieDataType.RANDOM_TAGLINE,
                MovieDataType.GENRES,
                MovieDataType.CERTIFICATE,
                MovieDataType.OFFICIAL_SITES,
                MovieDataType.COUNTRIES,
                MovieDataType.LANGUAGES,
                MovieDataType.RELEASE_DATE,
                MovieDataType.BUDGET,
                MovieDataType.CUMULATIVE_WORLDWIDE_GROSS,
                MovieDataType.RUNTIME,
                MovieDataType.SOUND_MIXES,
                MovieDataType.COLOR,
                MovieDataType.ASPECT_RATIO,
                MovieDataType.POSTER,
                MovieDataType.TAGLINES
        );


        assertTrue(result.getMovies().size() > 0);
        Movie movie = result.getMovies().get(0);

        assertTrue(movie.isDataTypeRetrieved(MovieDataType.ID));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.TITLE));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.ORIGINAL_TITLE));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.YEAR));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.STORYLINE));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.RANDOM_TAGLINE));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.GENRES));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.CERTIFICATE));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.OFFICIAL_SITES));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.COUNTRIES));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.LANGUAGES));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.RELEASE_DATE));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.BUDGET));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.CUMULATIVE_WORLDWIDE_GROSS));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.RUNTIME));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.SOUND_MIXES));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.COLOR));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.ASPECT_RATIO));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.POSTER));
        assertTrue(movie.isDataTypeRetrieved(MovieDataType.TAGLINES));

        assertEquals("0088247", movie.getId());
        assertEquals("The Terminator", movie.getOriginalTitle());
        assertEquals(Integer.valueOf(1984), movie.getYear());

        assertEquals(TERMINATOR_STORYLINE, movie.getStoryline());
        //assertEquals(TERMINATOR_TAGLINES, movie.getRandomTagline());
        assertTrue(movie.getGenres().contains("Action"));
        assertTrue(movie.getGenres().contains("Sci-Fi"));

        assertEquals("16+", movie.getCertificate());

        //assertTrue(movie.getOfficialSites().contains("Facebook"));
        assertTrue(movie.getOfficialSites().size() > 0);
        assertEquals("Facebook", movie.getOfficialSites().get(0).getTitle());
        //assertEquals(TERMINATOR_FACEBOOK_URL, movie.getOfficialSites().get(0).getUrl()); //not comparable, dynamic link

        assertTrue(movie.getCountries().size() > 0);
        assertTrue(movie.getCountries().contains("UK"));
        assertTrue(movie.getCountries().contains("USA"));

        assertTrue(movie.getLanguages().size() > 0);
        assertTrue(movie.getLanguages().contains("English"));
        assertTrue(movie.getLanguages().contains("Spanish"));

        assertEquals("26 October 1984 (USA)", movie.getReleaseDate());

        assertEquals("$6,400,000", movie.getBudget());
        assertEquals("$40,000,000", movie.getCumulativeWorldwideGross());

        assertEquals("107 min", movie.getRuntime());
        assertEquals("Color", movie.getColor());
        assertEquals("1.85 : 1", movie.getAspectRatio());

        assertEquals(TERMINATOR_POSTER_LINK, movie.getPosterLink());


        //sound mixes
        assertTrue(movie.getSoundMixes().size() > 0);
        boolean hasMono=false, hasDolby=false, hasDTS=false;
        String descrMono="", descrDolby="", descrDTS="";
        for (SoundMix soundMix : movie.getSoundMixes()) {
            switch (soundMix.getName()) {
                case "Mono" : hasMono = true; descrMono = soundMix.getDescription(); break;
                case "Dolby" : hasDolby = true; descrDolby = soundMix.getDescription();  break;
                case "DTS" : hasDTS = true; descrDTS = soundMix.getDescription();  break;
            }
        }
        assertTrue(hasMono);
        assertTrue(hasDolby);
        assertTrue(hasDTS);
        assertEquals("(original release)", descrMono);
        assertEquals("(DVD Re-Release)", descrDolby);
        assertEquals("(DTS HD Master Audio)", descrDTS);


        // taglines
        assertNotNull(movie.getTaglineList());
        assertEquals(5, movie.getTaglineList().getTaglines().size());

        boolean hasTagline1 = false, hasTagline2 = false, hasTagline3 = false, hasTagline4 = false, hasTagline5 = false;
        for (Tagline tagline : movie.getTaglineList().getTaglines()) {
            switch (tagline.getText()) {
                case TERMINATOR_TAGLINE_1 : hasTagline1 = true; break;
                case TERMINATOR_TAGLINE_2 : hasTagline2 = true; break;
                case TERMINATOR_TAGLINE_3 : hasTagline3 = true; break;
                case TERMINATOR_TAGLINE_4 : hasTagline4 = true; break;
                case TERMINATOR_TAGLINE_5 : hasTagline5 = true; break;
            }
        }

        assertTrue(hasTagline1);
        assertTrue(hasTagline2);
        assertTrue(hasTagline3);
        assertTrue(hasTagline4);
        assertTrue(hasTagline5);
    }
}
