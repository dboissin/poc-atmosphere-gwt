package com.blogspot.dboissin.simplegallery.server.util;

import java.util.Collections;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

public class DozerConverter implements GenericConverter, InitializingBean {
    
    /** Mapper Dozer */
    private Mapper mapper;


    // ---------------------------------------------------------------------
    // Implémentation de GenericConverter
    // ---------------------------------------------------------------------

    /**
     * <p>
     * Dozer peut convertir tout objet en autre objet
     */
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Object.class, Object.class));
    }

    /**
     * {@inheritDoc}
     */
    public Object convert(Object aSource, TypeDescriptor aSourceType, TypeDescriptor aTargetType) {
        return mapper.map(aSource, aTargetType.getType());
    }

    // ---------------------------------------------------------------------
    // Implémentation de InitializingBean
    // ---------------------------------------------------------------------

    /**
     * <p>
     * Si la propriété mapper n'est pas initialisée, alors initialise un mapper
     * Dozer avec le construteur vide (<code>new DozerBeanMapper()</code>).
     * </p>
     */
    public void afterPropertiesSet() {
        if (mapper == null) {
            mapper = new DozerBeanMapper();
        }
    }

    // ---------------------------------------------------------------------
    // Accesseurs
    // ---------------------------------------------------------------------

    /**
     * @param aMapper
     *            [obligatoire] le mapper Dozer à utiliser
     */
    public void setMapper(final Mapper aMapper) {
        mapper = aMapper;
    }

    public Mapper getMapper() {
        return mapper;
    }

}