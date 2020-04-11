package com.javainuse.main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.javainuse.model.*;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;

import org.kie.api.KieBaseConfiguration;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class DroolsTest {

	public static void main(String[] args) throws DroolsParserException,
			IOException {
		DroolsTest droolsTest = new DroolsTest();
		droolsTest.executeDrools();
	}

	public void executeDrools() throws DroolsParserException, IOException {
		KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		knowledgeBuilder.add(ResourceFactory.newClassPathResource("com/rule/FRules.drl"), ResourceType.DRL);
		knowledgeBuilder.add(ResourceFactory.newClassPathResource("com/rule/TRules.drl"), ResourceType.DRL);
		knowledgeBuilder.add(ResourceFactory.newClassPathResource("com/rule/RRules.drl"), ResourceType.DRL);
		knowledgeBuilder.add(ResourceFactory.newClassPathResource("com/rule/AttackVectorRules.drl"), ResourceType.DRL);

		KieBaseConfiguration kbaseConfig =
				KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
		kbaseConfig.setOption(EqualityBehaviorOption.EQUALITY);
		KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase(kbaseConfig);
		knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
		StatefulKnowledgeSession workingMemory = knowledgeBase.newStatefulKnowledgeSession();

		Application app = getApp();

		workingMemory.insert(app);
		workingMemory.fireAllRules();
		Collection<?> validThreat = workingMemory.getObjects();
		for(Object o : validThreat) {
			if(o instanceof Threats){
				System.out.println(o.toString());
			}
			if(o instanceof AttackVector){
				System.out.println(o.toString());
			}
		}
		workingMemory.dispose();

		System.out.println("==== generics === ");
	}

	private Application getApp() {
		String appName = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Input the Test Name: ");
			appName = br.readLine();
		}
		catch (Exception e){}

		Application app = new Application(appName);
		List<String> features = ReadFeature();
		List<String> risks = ReadRisks(appName);
		List<String> techs = ReadTechs(appName);
		app.setFeatures(features);
		app.setRisks(risks);
		app.setTechs(techs);
		return app;
	}

	private List<String> ReadFeature() {
		List<String> feature = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Input the feature you test: ");
			String[] temp = br.readLine().split(",");
			for(String s : temp) {
				feature.add(s);
			}
		}
		catch (Exception e){}

		return feature;
	}

	private List<String> ReadRisks(String name) {
		List<String> biz = new ArrayList<String>();
		try {
			File myObj = new File("risks.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String tempname = data.split(":")[0];
				if(tempname.equals(name)){
					String[] temp = data.split(":")[1].split(",");
					for(String s : temp) {
						biz.add(s);
					}
					break;
				}
				else{
					System.out.println("No such App");
				}
				System.out.println("Read Risks...");
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return biz;
	}

	private List<String> ReadTechs(String name) {
		List<String> biz = new ArrayList<String>();
		try {
			File myObj = new File("techs.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String tempname = data.split(":")[0];
				if(tempname.equals(name)){
					String[] temp = data.split(":")[1].split(",");
					for(String s : temp) {
						biz.add(s);
					}
					break;
				}
				else{
					System.out.println("No such App");
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return biz;
	}
}
