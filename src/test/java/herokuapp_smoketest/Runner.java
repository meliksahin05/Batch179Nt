package herokuapp_smoketest;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({C01PutBooking.class, C02GetBooking.class})
public class Runner {
}
