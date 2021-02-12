package org.acme.resteasyjackson;

import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

@Path("/resteasy-jackson/quarks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JacksonResource {

    private static final Logger LOG = Logger.getLogger(JacksonResource.class);

    private final Set<Quark> quarks = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public JacksonResource() {
        quarks.add(new Quark("Up", "The up quark or u quark (symbol: u) is the lightest of all quarks, a type of elementary particle, and a major constituent of matter."));
        quarks.add(new Quark("Strange", "The strange quark or s quark (from its symbol, s) is the third lightest of all quarks, a type of elementary particle."));
        quarks.add(new Quark("Charm", "The charm quark, charmed quark or c quark (from its symbol, c) is the third most massive of all quarks, a type of elementary particle."));
        quarks.add(new Quark("???", null));
    }

    @GET
    @Path("/faq")
    public String fac() throws Exception {
        long start = System.currentTimeMillis();
        BigInteger res = BigInteger.ONE;

        for(int i = 2; i < 7500; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }

        LOG.debug(System.currentTimeMillis() - start + " ms");

        return res.toString();
    }

    @GET
    public Set<Quark> list() throws Exception {
        Thread.sleep(500);
        return quarks;
    }

    @POST
    public Set<Quark> add(Quark quark) {
        quarks.add(quark);
        return quarks;
    }

    @DELETE
    public Set<Quark> delete(Quark quark) {
        quarks.removeIf(existingQuark -> existingQuark.name.contentEquals(quark.name));
        return quarks;
    }

    public static class Quark {
        public String name;
        public String description;

        public Quark() {
        }

        public Quark(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }
}
