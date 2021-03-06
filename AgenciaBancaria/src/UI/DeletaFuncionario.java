/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BANCO.ConectaBD;
import CLASS.Cliente;
import CLASS.Funcionario;
import DAO.AgenciaDAO;
import DAO.ClienteDAO;
import DAOCODE.AgenciaCode;
import DAOCODE.ClienteCode;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gustavo
 */
public class DeletaFuncionario extends javax.swing.JInternalFrame {

    /**
     * Creates new form ListaCliente
     */
    DefaultTableModel dtm;
    
    public DeletaFuncionario() {
        super("Deletar Funcionario",
              false, //resizable
               true, //closable
              false, //maximizable
               true);//iconifiable 
        initComponents();
        dtm = (DefaultTableModel) jtable.getModel();
        setTable();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        num_func = new javax.swing.JTextField();
        deleta = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtable = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setTitle("Buscar Cliente");

        jLabel1.setText("Num Funcional:");

        deleta.setText("Deletar");
        deleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletaActionPerformed(evt);
            }
        });

        jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num. Funcional", "Nome", "Agencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jtable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(num_func))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleta, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(num_func, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletaActionPerformed
    
        int resp;
        AgenciaCode del = new AgenciaCode();
        
        
        if(!ConectaBD.testaBD("SELECT c.n_gerente FROM cliente c WHERE c.n_gerente="+num_func.getText()+";")){//não é gerente
            if(ConectaBD.testaBD("SELECT f.num_superv FROM funcionarios f WHERE f.num_superv="+num_func.getText()+";")){//é supervisor
                resp = JOptionPane.showConfirmDialog(this,"Este é um supervisor, deseja remover?");

                if(resp == JOptionPane.YES_OPTION){//apertou sim
                    del.delete("DELETE FROM funcionarios f WHERE f.num_funcional="+num_func.getText()+";");
                    setTable();
                }               

            }else if(ConectaBD.testaBD("SELECT * FROM funcionarios f WHERE f.num_funcional="+num_func.getText()+";")){//existe
                    del.delete("DELETE FROM funcionarios f WHERE f.num_funcional="+num_func.getText()+";");
                    setTable();
            }else//não existe
                JOptionPane.showMessageDialog(this, "Confira se o numero esta correto!");
        }else
                JOptionPane.showMessageDialog(this, "Não é possível remover este funcionario, o mesmo é gerente!");
           
    }//GEN-LAST:event_deletaActionPerformed

     private void setTable()
     {
           dtm.setRowCount(0);
        AgenciaDAO cdao = new AgenciaCode();
        ArrayList<Funcionario> funcionarios;
        
        funcionarios = cdao.getAllFuncionarios();

        for(Funcionario c:funcionarios){
            dtm.addRow(new String[]{c.getNum_funcional(),c.getNome(),c.getAgencia()});
        } 
     }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jtable;
    private javax.swing.JTextField num_func;
    // End of variables declaration//GEN-END:variables
}
