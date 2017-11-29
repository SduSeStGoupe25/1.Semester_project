/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author madsd
 */
class DomainGameMapper {

    Object map(Object objectToBeMapped) {
        if(objectToBeMapped == null) return null;
        Object instanceOfClass = null;
        try {
            //NAME ON THE CLASS TO BE MAPPED TO
            System.out.println("Class " + objectToBeMapped.getClass());
            String s = objectToBeMapped.getClass().getName().replace("Data", "Domain");
            System.out.println("STRING " + s);

            //CLASS TO BE MAPPED TO
            Class myClass = null;

            //CREATES THE CLASS
            myClass = Class.forName(s);
            System.out.println(myClass);

            //GETS THE NO-ARGS CONSTRUCTOR 
            Constructor constructor = myClass.getConstructor();

            //CREATES AN INSTANCE OF THE CLASS
            instanceOfClass = constructor.newInstance();

            runThroughClass(myClass, instanceOfClass, objectToBeMapped);

        } catch (NoSuchMethodException ex) {
            Logger.getLogger(DomainGameMapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DomainGameMapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DomainGameMapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DomainGameMapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(DomainGameMapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DomainGameMapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DomainGameMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instanceOfClass;
    }

    private void runThroughClass(Class c, Object instanceOfClass, Object objectToBeMapped) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Class i : c.getInterfaces()) {
            runThroughMethods(i, instanceOfClass, objectToBeMapped);

            // runThroughClass(i, instanceOfMyClass, objectToBeMapped);
        }
    }

    private void runThroughMethods(Class<?> c, Object instanceOfClass, Object objectToBeMapped) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //FOR ALL METHODS IN THE INTERFACE
        for (Method currentMethod : c.getMethods()) {
            //THE NAME ON THE METHOD ON THE OBJECT
            String nameOnSetterMethod = null;
            if (currentMethod.getName().startsWith("get")) {
                nameOnSetterMethod = currentMethod.getName().replace("get", "set");
            } else {
                continue;
            }

            //THE METHOD WITH THE NAME M AND THE RETURN TYPE
            Method me = instanceOfClass.getClass().getMethod(nameOnSetterMethod, currentMethod.getReturnType());

            Class returnTypeForMethod = currentMethod.getReturnType();

            if (Collection.class.isAssignableFrom(returnTypeForMethod)) {

                me.invoke(instanceOfClass, mapCollection(currentMethod, objectToBeMapped, instanceOfClass));
            } else if (Map.class.isAssignableFrom(returnTypeForMethod)) {

                me.invoke(instanceOfClass, mapMap(currentMethod, objectToBeMapped, instanceOfClass));

            } else {

                if (returnTypeForMethod.isInterface()) {
                    //INVOKES METHOD ME 
                    me.invoke(instanceOfClass, map(currentMethod.invoke(objectToBeMapped)));
                } else {
                    //INVOKES METHOD ME 
                    me.invoke(instanceOfClass, currentMethod.invoke(objectToBeMapped));
                }
            }
        }
    }

    private Collection mapCollection(Method currentMethod, Object objectToBeMapped, Object instanceOfMyClass) throws IllegalAccessException, InvocationTargetException {
        Type genericFieldType = currentMethod.getGenericReturnType();

        if (genericFieldType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) genericFieldType;
            Type fieldArgTypes = type.getActualTypeArguments()[0];

            Class checkForInterface = (Class) fieldArgTypes;

            Collection tmpC = (Collection) currentMethod.invoke(objectToBeMapped);
            Collection retC = (Collection) currentMethod.invoke(instanceOfMyClass);

            if (checkForInterface.isInterface() && tmpC != null) {
                for (Object o : tmpC) {
                    retC.add(map(o));
                }
                return retC;
            } else {
                return tmpC;
            }
        }
        throw new IllegalArgumentException("Input not a Collection");
    }

    private Map mapMap(Method currentMethod, Object objectToBeMapped, Object instanceOfMyClass) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Type genericFieldType = currentMethod.getGenericReturnType();

        if (genericFieldType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) genericFieldType;
            Type key = type.getActualTypeArguments()[0];
            Class k = (Class) key;

            Type value = type.getActualTypeArguments()[1];
            Class v = (Class) value;

            Map tmpMap = (Map) currentMethod.invoke(objectToBeMapped);
            Map retMap = (Map) currentMethod.invoke(instanceOfMyClass);

            if (tmpMap != null) {
                for (Object o : tmpMap.keySet()) {
                    retMap.put(
                            (k.isInterface()) ? map(o) : o,
                            (v.isInterface()) ? map(tmpMap.get(o)) : tmpMap.get(o));
                }
            }
            return retMap;
        }
        throw new IllegalArgumentException("Input not a Map");
    }
}
