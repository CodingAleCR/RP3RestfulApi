package cr.codingale.restfulapi;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Options;
import org.restlet.resource.ServerResource;

import java.util.HashSet;
import java.util.Set;

public class PotentiometerResource extends ServerResource {

    @Get("json")
    public Representation getPotentiometer() {
        JSONObject result = new JSONObject();
        try {
            result.put("result", PotentiometerModel.getReading());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getResponse().setAccessControlAllowOrigin("*");
        return new StringRepresentation(result.toString(), MediaType.APPLICATION_ALL_JSON);
    }

    @Options
    public void getCorsSupport() {
        Set<String> head = new HashSet<>();
        head.add("X-Requested-With");
        head.add("Content-Type");
        head.add("Accept");

        getResponse().setAccessControlAllowHeaders(head);

        Set<Method> methods = new HashSet<>();
        methods.add(Method.GET);
        methods.add(Method.POST);
        methods.add(Method.OPTIONS);

        getResponse().setAccessControlAllowMethods(methods);
        getResponse().setAccessControlAllowOrigin("*");
    }
}
