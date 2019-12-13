ansible-playbook ../ansible_copy/vm_create.yml
ansible-playbook -i ../ansible_copy/azure_rm.py -l ansible-node ../ansible_copy/vm_configure.yml
ansible-playbook -i ../ansible_copy/azure_rm.py -l ansible-node ../ansible_copy/vm_createKuber.yml

