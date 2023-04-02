package no.dnb.loan.realestateloanapi.config

import org.jetbrains.exposed.sql.Database
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.sql.DataSource

@Configuration
class ApplicationConfiguration(dataSource: DataSource) : WebMvcConfigurer {

    init {
        Database.connect(dataSource)
    }

    private val webContextPath: String = "/loan-api"

    override fun addViewControllers(registry: ViewControllerRegistry) {
        val reactApp = "forward:/index.html"
        registry.addViewController(webContextPath).setViewName(reactApp)
        registry.addViewController("$webContextPath/*").setViewName(reactApp)
        registry.addViewController("/").setViewName(reactApp)
        registry.addViewController("/home/page").setViewName(reactApp)
        registry.addViewController("/user/register").setViewName(reactApp)
        registry.addViewController("/admin/register").setViewName(reactApp)
        registry.addViewController("/customer/page").setViewName(reactApp)
        registry.addViewController("/adviser/page").setViewName(reactApp)
        registry.addViewController("$webContextPath/manifest.json").setViewName("forward:/manifest.json")
        registry.addViewController("$webContextPath/favicon.ico").setViewName("forward:/favicon.ico")
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("$webContextPath/static/**")
            .addResourceLocations("classpath:/static/static/")
        registry.addResourceHandler(
            "$webContextPath/favicon.ico",
            "$webContextPath/manifest.json",
        ).addResourceLocations("classpath:/static")
    }
}
