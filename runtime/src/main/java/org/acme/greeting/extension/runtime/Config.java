package org.acme.greeting.extension.runtime;

import io.quarkus.runtime.annotations.ConfigDocEnum;
import io.quarkus.runtime.annotations.ConfigDocEnumValue;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithConverter;
import org.eclipse.microprofile.config.spi.Converter;

@ConfigRoot(phase = ConfigPhase.RUN_TIME)
@ConfigMapping(prefix = "org.acme")
public interface Config {

    enum Style {
        @ConfigDocEnumValue("classico-classic")
        CLASSICO_CLASSIC,
        @ConfigDocEnumValue("funky")
        FUNKY
    }

    class StyleConverter implements Converter<Style> {

        @Override
        public Style convert(String value) throws IllegalArgumentException, NullPointerException {
            return Style.valueOf(value.toUpperCase().replaceAll("-", "_"));
        }
    }

    /**
     * My wonderful property.
     *
     * @asciidoclet
     */
    @WithConverter(StyleConverter.class)
    @ConfigDocEnum(enforceHyphenateValues = true)
    Style style();
}
