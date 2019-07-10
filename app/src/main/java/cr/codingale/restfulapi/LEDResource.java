package cr.codingale.restfulapi;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Options;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.HashSet;
import java.util.Set;

public class LEDResource extends ServerResource {

    @Get("json")
    public Representation getState() {
        JSONObject result = new JSONObject();
        try {
            result.put("estado", LEDModel.getState());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getResponse().setAccessControlAllowOrigin("*");
        return new StringRepresentation(result.toString(), MediaType.APPLICATION_ALL_JSON);
    }

    @Post("json")
    public Representation postState(Representation entity) {
        JSONObject query = new JSONObject();
        JSONObject fullResult = new JSONObject();
        String result;
        try {
            JsonRepresentation json = new JsonRepresentation(entity);
            query = json.getJsonObject();
            boolean state = (boolean) query.get("estado");
            Log.d(this.getClass().getSimpleName(), "Nuevo estado del LED: " + state);
            if (LEDModel.setState(state)) result = "ok";
            else result = "error";
        } catch (Exception e) {
            e.printStackTrace();
            result = "error";
        }
        try {
            fullResult.put("resultado", result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        getResponse().setAccessControlAllowOrigin("*");
        return new StringRepresentation(fullResult.toString(), MediaType.APPLICATION_ALL_JSON);
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
