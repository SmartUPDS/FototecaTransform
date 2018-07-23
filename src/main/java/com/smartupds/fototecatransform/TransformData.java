package com.smartupds.fototecatransform;

import eu.delving.x3ml.X3MLEngineFactory;
import java.io.File;
import lombok.extern.log4j.Log4j;

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
    private final X3MLEngineFactory.OutputFormat outputFormat;
    
    /** Creates a new instance and defines the default resources for transforming data (i.e. X3ML mappings, generator policy, 
     * UUID size and output format). 
     * 
     * @param x3mlMapppingsFile the file containing the X3ML mappings 
     * @param generatorPolicyFile the file containing the generator policy definitions
     * @param uuidSize the size of the generated UUIDs (use negative number for default UUID size)
     * @param outputFormat the format of the transformed data */
    public TransformData(File x3mlMapppingsFile, File generatorPolicyFile, int uuidSize, X3MLEngineFactory.OutputFormat outputFormat){
        this.x3mlMappings=x3mlMapppingsFile;
        log.info("X3ML Mappings file path: "+this.x3mlMappings);
        this.generatorPolicy=generatorPolicyFile;
        log.info("Generator Policy file path: "+this.generatorPolicy);
        this.uuidSize=uuidSize;
        log.info("UUID Size: "+this.uuidSize);
        this.outputFormat=outputFormat;
        log.info("Output format: "+this.outputFormat);
    }
    
    /** Transforms the contents of the source file and exports the contents in the target file. 
     * To do so, it uses X3ML mappings descriptions and generator specification resources that 
     * have been declared during the initialization. 
     * 
     * @param from the file containing resources in the original format
     * @param to the file were the transformed contents will be exported */
    private void transformResource(File from, File to){
        
    }
    
    public static void main(String[] args){
        
    }
}