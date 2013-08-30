package framework.api.webservices.systemparameters;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import framework.api.webservices.BaseController;
import framework.core.domain.configuration.Configuration;
import framework.core.domain.configuration.ConfigurationService;
import framework.core.domain.role.Role;

@Named
@Path("/systemParameter")
public class SystemParameterController extends BaseController {

    private static final long serialVersionUID = 200605594031531073L;
    private final ConfigurationService configurationService;

    @Inject
    protected SystemParameterController(ConfigurationService configurationService) {
        this.configurationService= configurationService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(value = { Role.ADMINISTRATORS })
    public List<SystemParameterResponse> loadSystemParameters() {
        final List<SystemParameterResponse> list = new ArrayList<SystemParameterResponse>();
        final List<Configuration> configurations = this.configurationService.findAllActiveConfiguration(this.getAuthenticatedUser());
        for (final Configuration configuration : configurations) {
            SystemParameterResponse systemParameterResponse = new SystemParameterResponse(configuration);
            systemParameterResponse.setDisplay(this.getMessage(systemParameterResponse.getDisplay()));
            list.add(systemParameterResponse);
        }
        return list;
    }

    /*@GET
    @Path(value = "/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(value = { Role.ADMINISTRATORS })
    public SystemParameterResponse loadSystemParameters(@PathParam(value = "code") String code) {
        final String clientName = this.getAuthenticatedUser().getClient().getName();
        final ParameterCode parameterCode = ParameterCode.valueOf(code);
        final SystemParameter param = this.systemParameterService.findSystemParamByCode(parameterCode, clientName);
        return new SystemParameterResponse(param);
    }*/

}
