package n.rrs.app;

import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class Restapp extends ResourceConfig{
	
	public Restapp(){
		packages("n.rrs.rest");
		register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath("Project-API/api");
        beanConfig.setResourcePackage("n.rrs.rest");
        beanConfig.setTitle("RESTapp API Documentation");
        beanConfig.setScan(true);
	}

}
