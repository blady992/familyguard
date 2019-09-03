package pl.aagenda.familyguard.datastorage.common.mapstruct;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

import java.util.IdentityHashMap;
import java.util.Map;

public class CyclicGraphContext {
    private Map<Object, Object> knownInstances = new IdentityHashMap<>();

    @BeforeMapping
    public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
        return (T) knownInstances.get( source );
    }

    @BeforeMapping
    public <T> void storeMappedInstance(Object source, @MappingTarget T target) {
        knownInstances.put( source, target );
    }
}
