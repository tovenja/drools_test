package com.mi.drools;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * Created by blank on 2016-03-11 上午11:04.
 */
public class DroolsTester {

    public static final class User {
        int money;
        int empty;
        int totals;
        int cover;


        public int getCover() {
            return cover;
        }

        public void setCover(int cover) {
            this.cover = cover;
        }

        public User(int money) {
            this.money = money;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getEmpty() {
            return empty;
        }

        public void setEmpty(int empty) {
            this.empty = empty;
        }

        public int getTotals() {
            return totals;
        }

        public void setTotals(int totals) {
            this.totals = totals;
        }
    }


    public static void main(String[] args) {

        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("drools/config/a.drl"), ResourceType.DRL);
        if (builder.hasErrors()) {
            System.out.println("Wrong rules");
            for (KnowledgeBuilderError knowledgeBuilderError : builder.getErrors()) {
                System.out.println(knowledgeBuilderError);
            }
        }
        long start = System.currentTimeMillis();
        KnowledgeBase kb = KnowledgeBaseFactory.newKnowledgeBase();
        kb.addKnowledgePackages(builder.getKnowledgePackages());
        StatefulKnowledgeSession session = kb.newStatefulKnowledgeSession();
        session.insert(new User(10));
        session.fireAllRules();
        session.dispose();
        System.out.println(System.currentTimeMillis() - start + "ms");
    }
}
