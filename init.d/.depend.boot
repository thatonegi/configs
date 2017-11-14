TARGETS = console-setup mountkernfs.sh alsa-utils apparmor x11-common lm-sensors hostname.sh screen-cleanup pppd-dns dns-clean plymouth-log udev mountdevsubfs.sh netfilter-persistent brltty procps hwclock.sh checkroot.sh cryptdisks-early cryptdisks networking urandom lvm2 checkfs.sh mountnfs-bootclean.sh mountnfs.sh bootmisc.sh mountall-bootclean.sh mountall.sh checkroot-bootclean.sh kmod
INTERACTIVE = console-setup udev checkroot.sh cryptdisks-early cryptdisks checkfs.sh
udev: mountkernfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
netfilter-persistent: mountkernfs.sh
brltty: mountkernfs.sh udev
procps: mountkernfs.sh udev
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh
cryptdisks-early: checkroot.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
networking: mountkernfs.sh urandom procps dns-clean
urandom: hwclock.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks checkroot.sh lvm2
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
bootmisc.sh: mountnfs-bootclean.sh mountall-bootclean.sh udev checkroot-bootclean.sh
mountall-bootclean.sh: mountall.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
checkroot-bootclean.sh: checkroot.sh
kmod: checkroot.sh
