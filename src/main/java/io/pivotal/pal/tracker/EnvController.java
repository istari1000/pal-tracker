package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController
{
    private final String port, memoryLimit, cfInstanceIndex, cfInstanceAddress;
    public EnvController(
            @Value("${port:NOT SET}")String port,
            @Value("${memory.limit:NOT SET}")String memoryLimit,
            @Value("${cf.instance.index:NOT SET}")String cfInstanceIndex,
            @Value("${cf.instance.address:NOT SET}")String cfInstanceAddress)
    {
        this.port = port;
        this.memoryLimit = memoryLimit;
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddress = cfInstanceAddress;
    }
    @GetMapping("/env")
    public Map<String, String> getEnv()
    {
        Map<String, String> returnMap = new HashMap<String, String>();
        returnMap.put("PORT", port);
        returnMap.put("MEMORY_LIMIT", memoryLimit);
        returnMap.put("CF_INSTANCE_INDEX", cfInstanceIndex);
        returnMap.put("CF_INSTANCE_ADDR", cfInstanceAddress);
        return returnMap;
    }
}
