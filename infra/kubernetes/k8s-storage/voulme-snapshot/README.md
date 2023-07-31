# Enable Dynamic Provisioning on Minikube 
# https://minikube.sigs.k8s.io/docs/tutorials/volume_snapshots_and_csi/

![Alt text](./image/volume-snapshot.png?raw=true "volume-snapshot")

![Alt text](./image/volume-snapshot-seq.png?raw=true "volume-snapshot")

![Alt text](./image/volume-snapshot-restore.png?raw=true "volume-snapshot")

![Alt text](./image/volume-snapshot-restore-visual.png?raw=true "volume-snapshot")

![Alt text](./image/csi-snapshotter.png?raw=true "volume-snapshot")


mk addons enable volumesnapshots

mk addons enable csi-hostpath-driver

k get sc

k get volumeSnapshotClass



cd manifests

k get sc,pvc,pv

k apply -f sc.yaml

k get sc,pvc,pv

k apply -f pvc.yaml

k get sc,pvc,pv

k apply -f deployment.yaml

k get sc,pvc,pv,pods

k apply -f deployment1.yaml

k get sc,pvc,pv,pods

k exec -it deploy/nginx -- bash

echo "HelloWorld" > /vol/sonalTesting

exit

k exec -it deploy/nginx -- cat /vol/sonalTesting

k exec -it deploy/nginx1 -- cat /vol/sonalTesting

cd volumeSnapshot

k get volumeSnapshotClass,volumeSnapshot,volumeSnapshotContent

k apply -f volumeSnapshotClass.yaml

k get volumeSnapshotClass,volumeSnapshot,volumeSnapshotContent

k apply -f volumeSnapshot.yaml

k get volumeSnapshotClass,volumeSnapshot,volumeSnapshotContent

cd ..

k delete -f deployment.yaml

k delete -f deployment1.yaml

k get sc,pvc,pv

k delete -f pvc.yaml

k get sc,pvc,pv

k get volumeSnapshotClass,volumeSnapshot,volumeSnapshotContent

k get sc,pvc,pv

k apply -f ./volumeSnapshot/restore/pvc-from-volume-snapshot.yaml

k get sc,pvc,pv

k apply -f deployment.yaml
k apply -f deployment1.yaml

k get sc,pvc,pv,pods


k exec -it deploy/nginx -- cat /vol/sonalTesting

k exec -it deploy/nginx1 -- cat /vol/sonalTesting