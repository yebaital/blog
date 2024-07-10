import {Button} from "@/components/ui/button";
import {PlusIcon} from "@radix-ui/react-icons";
import Link from "next/link";

export default function Dashboard() {
    return (
        <div>
            <div className="flex items-center justify-between">
                <h1 className="text-2xl font-bold">Blogs</h1>
                <Link href="/dashboard/blog/create">
                    <Button variant="outline">Create <PlusIcon/></Button>
                </Link>

            </div>
        </div>
    );
}