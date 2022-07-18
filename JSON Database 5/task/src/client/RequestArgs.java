package client;

import com.beust.jcommander.Parameter;

public class RequestArgs {
    @Parameter(names = "-t", description = "Type of request")
    public String type;

    @Parameter (names = "-k", description = "Key")
    public String key;

    @Parameter (names = "-v", description = "Some text", variableArity = true)
    public String value;

    @Parameter (names = "-in", description = "Input file")
    public String fileName;
}
