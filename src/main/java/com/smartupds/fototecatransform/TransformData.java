package com.smartupds.fototecatransform;

import eu.delving.x3ml.X3MLEngineFactory;
import java.io.File;
import lombok.extern.log4j.Log4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** A Transformer class that uses X3ML Engine for transforming Fototeca XML resources
 * 
 * @author Yannis Marketakis
 * @author Nikos Minadakis 
 */
@Log4j
public class TransformData {
    private final File x3mlMappings;
    private final File generatorPolicy;
    private final int uuidSize;
    
    /** Creates a new instance and defines the default resources for transforming data (i.e. X3ML mappings, generator policy 
     *  and UUID size). It is initialized using Spring. 
     * 
     * @param context The application context as defined from Spring */
    public TransformData(ApplicationContext context){
        this.x3mlMappings=context.getBean(Resources.X3ML_MAPPINGS_BEAN,File.class);
        log.info("X3ML Mappings file path: "+this.x3mlMappings);
        this.generatorPolicy=context.getBean(Resources.GENERATOR_POLICY_BEAN,File.class);
        log.info("Generator Policy file path: "+this.generatorPolicy);
        this.uuidSize=context.getBean(Resources.UUID_SIZE_BEAN,int.class);
        log.info("UUID Size: "+this.uuidSize);
    }
    
    /** Transforms the contents of the source file and exports the contents in the target file. 
     * To do so, it uses X3ML mappings descriptions and generator specification resources that 
     * have been declared during the initialization. 
     * 
     * @param from the file containing resources in the original format
     * @param to the file were the transformed contents will be exported */
    private void transformResource(File from, File to){
        log.info("Transforming file "+from+" to "+to);
        X3MLEngineFactory.create()
                         .withMappings(this.x3mlMappings)
                         .withGeneratorPolicy(this.generatorPolicy)
                         .withUuidSize(this.uuidSize)
                         .withInputFiles(from)
                         .withOutput(to, X3MLEngineFactory.OutputFormat.RDF_XML)
                         .execute();
    }
    
    /** Transforms all the XML resources from the source folder and exports the transformed 
     * contents to the destination folder. For each resource in the source folder it creates a file in the 
     * destination folder with the same name (and a different extension). 
     * 
     * @param fromFolder the folder containing the source files
     * @param toFolder the folder containing the transformed files */
    public void transformAllResources(File fromFolder, File toFolder){
        log.info("Transforming sources from folder "+fromFolder+" to folder "+toFolder);
        for(File sourceFile : fromFolder.listFiles()){
            transformResource(sourceFile, new File(toFolder+"/"+sourceFile.getName().replace(".xml", ".rdf")));
        }
    }
    
    public static void main(String[] args){
        ApplicationContext context=new ClassPathXmlApplicationContext(Resources.BEANS_FILE);
        TransformData transformData=new TransformData(context);
        transformData.transformAllResources(context.getBean(Resources.SOURCE_FILES_BEAN, File.class),
                                            context.getBean(Resources.TRANSFORMED_FILES_BEAN, File.class));
    }
}